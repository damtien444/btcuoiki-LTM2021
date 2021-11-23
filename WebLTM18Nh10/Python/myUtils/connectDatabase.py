import sqlite3
class connectToDatabase:
    def __init__(self, path_sql):
        self.db = sqlite3.connect(path_sql)
        self.cursor = self.db.cursor()
        self.ma_session = ""

    def set_ma_session(self, ma_session):
        self.ma_session = ma_session

    def getPath_from_maSession(self):
        self.cursor.execute(f"select link_to_file from HISTORY where ma_session={self.ma_session}")
        image_path = self.cursor.fetchone()
        return image_path

    def update_result(self, result):
        # self.cursor.execute(f'update HISTORY set result = {result} where ma_session={self.ma_session}')
        self.cursor.execute("update HISTORY set result = ? where ma_session = ?", (result, self.ma_session))
        self.db.commit()

    def update_lastTimeUpdate(self, lastTimeUpdate):
        self.cursor.execute("update HISTORY set timestamp = ? where ma_session = ?", (lastTimeUpdate, self.ma_session))
        self.db.commit()

    def update_status(self, status):
        self.cursor.execute("update HISTORY set status = ? where ma_session = ?", (status, self.ma_session))
        self.db.commit()

    def update_isRunning(self, is_running):
        self.cursor.execute("update HISTORY set is_running = ? where ma_session = ?", (is_running, self.ma_session))
        self.db.commit()

if __name__ == '__main__':
    path = "G:/NAM 4/LTM/eclipse/MyLoginDB SQLite.db"
    sql = connectToDatabase(path)