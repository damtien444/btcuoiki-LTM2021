import sqlite3
import time


class update_to_SQL:
    def __init__(self, path_sql):
        self.db = sqlite3.connect(path_sql)
        self.cursor = self.db.cursor()

    def updateSession(self, ma_session, status, result, is_running):
        last_update = time.time()
        self.cursor.execute('''update HISTORY set status = ?, result = ?, is_running = ?, last_update = ? where ma_session = ?''', (status, result, is_running, last_update, ma_session))
        self.db.commit()


# path đến sql db

path = "C:\\Users\\damti\\OneDrive - Danang University of Technology\\OneDrive - The University of Technology\\Desktop\\Study\\LTM va  TH\\src\\JSP _ Trigangle\\MyLoginDB SQLite.db"

sql = update_to_SQL(path)

sql.updateSession(2, 39, None, False)