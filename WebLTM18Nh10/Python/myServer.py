import socket
from myUtils.queueThread import MyQueue
localIP = '127.0.0.1'
localPort = 9900

def openServer(localIP, localPort):
    UDPServerSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
    UDPServerSocket.bind((localIP, localPort))
    print('Opening server...')
    return UDPServerSocket
def start_running():
    try:
        myServer = openServer(localIP, localPort)
        path = "G:/NAM 4/LTM/eclipse/MyLoginDB SQLite.db"
        myQ = MyQueue(mysql_path=path)
        myQ.load_weight("Violence_video/src/weights/convlstm3d_128_16.pth")
        myQ.startThread()
        while (True):
            bytesAddressPair = myServer.recvfrom(1024)
            ma_session = bytesAddressPair[0].decode()
            print(f"Ma Session: {ma_session}")
            myQ.putItem(ma_session)
            myQ.joinQueue()
    except BaseException as error:
        print(error)
if __name__ == '__main__':
    start_running()