import cv2
import torch
from torch.nn import Softmax
import numpy as np
from Violence_video.src.modeling import ConvLSTM3D
from Violence_video.src.config import get_cfg
from albumentations import pytorch
import albumentations as A
import calendar
import time

class PredictAction():
    def __init__(self, cfg):
        self.cfg = cfg
        self.model = ConvLSTM3D(cfg)
        self.totensor = pytorch.transforms.ToTensorV2()
        self.normalize = A.Normalize()
        self.link_video = 0
        self.list_image = list()
        self.label = ["Violence", "Non Violence"]
        self.action = None
    def read_video(self, link_video="0"):

        self.link_video = link_video
        if self.link_video == "0":
            self.cap = cv2.VideoCapture(self.link_video, cv2.CAP_DSHOW)
        else:
            self.cap = cv2.VideoCapture(self.link_video)
        if self.cap.isOpened() == False:
            print("Error opening video stream or file")

    def show_video(self):

        while self.cap.isOpened():
            ret, frame = self.cap.read()
            if ret == True:
                cv2.imshow('Frame', frame)
                if cv2.waitKey(25) & 0xFF == ord('q'):
                    break
            else:
                break

        self.cap.release()
        cv2.destroyAllWindows()

    def load_model(self, link_model):

        ckpt = torch.load(link_model, "cpu")
        self.model.load_state_dict(ckpt.pop('state_dict'))
        self.model.sigmoid = Softmax(dim=1)
        if torch.cuda.is_available():
            self.model = self.model.cuda()

    def __update_list_image(self, frame):
        img = frame.copy()
        img = cv2.resize(src=img, dsize=(
            self.cfg.DATA.IMG_SIZE, self.cfg.DATA.IMG_SIZE))
        img_tensor = self.normalize(image=img)["image"]
        img_tensor = self.totensor(image=img_tensor)["image"]
        # print(img_tensor.shape)
        if len(self.list_image) < self.cfg.DATA.NUM_SLICES * self.cfg.DATA.STRIDE:
            self.list_image.append(img_tensor)
        else:
            self.list_image.pop(0)
            self.list_image.append(img_tensor)

    def __detect_activity(self):

        if len(self.list_image) < self.cfg.DATA.NUM_SLICES * self.cfg.DATA.STRIDE:
            self.action = None
        else:
            imgs = self.list_image[::self.cfg.DATA.STRIDE]
            imgs = torch.stack(imgs)
            imgs = imgs.reshape(1, self.cfg.DATA.NUM_SLICES, self.cfg.DATA.INP_CHANNEL, self.cfg.DATA.IMG_SIZE,
                                self.cfg.DATA.IMG_SIZE)
            if torch.cuda.is_available():
                imgs = imgs.cuda()

            with torch.no_grad():
                prob = self.model(imgs, self.cfg.DATA.NUM_SLICES)

            if torch.cuda.is_available():
                prob = prob.cpu()

            prob = prob.detach().numpy()
            i = np.argmax(prob, axis=1)
            if prob[:, i[0]] > 0.9:
                self.action = self.label[i[0]]

    def show_activity(self):

        while self.cap.isOpened():
            ret, frame = self.cap.read()
            if ret == True:
                self.__update_list_image(frame)
                self.__detect_activity()
                text = "activity: {}"
                org = (50, 50)
                # write the text on the input image
                cv2.putText(frame, text.format(
                    self.action), org, fontFace=cv2.FONT_HERSHEY_PLAIN, fontScale=5, color=(0, 255, 0), thickness=3)
                frame = cv2.resize(frame, (960, 640))
                cv2.imshow('Frame', frame)
                if cv2.waitKey(25) & 0xFF == ord('q'):
                    break
            else:
                break

        self.cap.release()
        cv2.destroyAllWindows()

    def predict_activity(self, mysql):
        count = 0
        list_action = []
        mysql.update_isRunning(True)
        total_frames = int(self.cap.get(cv2.CAP_PROP_FRAME_COUNT))
        while self.cap.isOpened():
            ret, frame = self.cap.read()
            if ret == True:
                count += 1
                self.__update_list_image(frame)
                self.__detect_activity()
                mysql.update_status(str(int(count/total_frames*100)) + "%")
                if len(self.list_image) >= self.cfg.DATA.NUM_SLICES * self.cfg.DATA.STRIDE:
                    list_action.append(self.action)
            else:
                break
        self.cap.release()
        cv2.destroyAllWindows()
        if(list_action.count(self.label[0])/len(list_action) > 1/4):
            mysql.update_result(self.label[0])
            print(f'Action: {self.label[0]}')
        else:
            mysql.update_result("Non Violence")
            print(f'Action: {self.label[1]}')
        mysql.update_isRunning(False)

if __name__ == '__main__':
    cfg = get_cfg()
    stream = PredictAction(cfg)
    stream.load_model("Violence_video/src/weights/convlstm3d_128_16.pth")
    stream.read_video("Violence_video/src/datasets/violence.mp4")
    stream.predict_activity()

