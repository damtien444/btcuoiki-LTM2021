import threading, queue
from Violence_video.src.config import get_cfg
from myUtils.predictAction import PredictAction
from myUtils.connectDatabase import connectToDatabase
class MyQueue():
    def __init__(self, mysql_path):
        self.myQueue = queue.Queue()
        self.myThread = threading.Thread(target=self.worker, daemon=True)
        self.cfg = get_cfg()
        self.stream = PredictAction(self.cfg)
        self.mysql_path = mysql_path

    def worker(self):
        while True:
            mysql = connectToDatabase(self.mysql_path)
            mysql.set_ma_session(self.myQueue.get())
            video_path = mysql.getPath_from_maSession()[0].replace("\\", "/")
            self.stream.read_video(video_path)
            self.stream.predict_activity(mysql)
            self.myQueue.task_done()

    def load_weight(self, model_path):
        self.stream.load_model(model_path)

    def startThread(self):
        self.myThread.start()

    def joinQueue(self):
        self.myQueue.join()

    def putItem(self, ma_session):
        self.myQueue.put(ma_session)

if __name__ == '__main__':
    path = "G:/NAM 4/LTM/eclipse/MyLoginDB SQLite.db"
    mysql = connectToDatabase(path)

    myQ = MyQueue(mysql)
    myQ.startThread()
    for item in range(30):
        myQ.putItem(item)

    myQ.joinQueue()
