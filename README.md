## Trang Web kiểm tra video chứa nội dung bạo lực

Bài tập cuối kì môn Lập trình mạng (11-2021), khoa Công nghệ Thông tin, trường Đại học Bách khoa, Đại học Đà Nẵng.
Người thực hiện: Đàm Quang Tiến, Nguyễn Nghĩa Thịnh, Trần Quang Trí.
Giáo viên: TS. Phạm Minh Tuấn

# A.	Cài đặt và deploy

Điều kiện: cài đặt python (và tạo environment riêng nếu muốn).

B1: Clone project từ git này.

B2: Tải apache tomcat 8.5 và thêm vào eclipse.

B3: Thêm sqlite-jdbc-3.7.2.jar vào Classpath của project, đồng thời, thêm sqlite-jdbc-3.7.2.jar vào lib của tomcat 8.5 (khởi động lại eclipse).

B4: Tạo thư mục tên “LTM_webapps” trong ổ C.

B4.1: Tạo thư mục “video” trong thư mục “LTM_webapps”.

B5: Copy “MyLoginDB SQLite.db” vào thư mục “C:\LTM_webapps”.

Cây thư mục tại ổ C như sau:
```
C:\
|
└─── LTM_webapps
        |     MyLoginDB SQLite.db
        └───  video
                |     video1.mp4 \\ các video được upload lên server sẽ được lưu tại đây   
                ....
```

B6: Trỏ CMD đến thư mục Python trong folder clone về từ git (..\btcuoiki-LTM20211\WebLTM18Nh10\Python), chạy câu lệnh “pip install -r requirements.txt”.

B6.1: Tải weight cho model và đặt tại thư mục Python.
Link: https://drive.google.com/file/d/1y7F_z3c5j0M9RJm9TFMmygtRdg4L_JeP/view?usp=sharing

B7: Trong thư mục Python, dùng cmd chạy câu lệnh python myServer.py.

B8: Trên eclipse, chạy Login.jsp bằng run on server.

## Xin lưu ý: Vì lý do môi trường trên các hệ thống khác nhau nên một vài thư viện có thể không được cài đầy đủ bởi câu lệnh trên, nên thực hiện pip install riêng với mỗi thư viện lỗi xuất hiện khi chạy myServer.py.


# B.	Hướng dẫn sử dụng

B1: Đăng nhập với tên abc với pass 123.

B2: Upload một video bất kì với kích thước không quá 100Mb.

-	Bấm browse

-	Chọn file video

-	Bấm submit

B3: Màn hình hiển thị lịch sử xử lý video của hệ thống.

B4: Có thể logout hoặc quay về home page để tiếp tục upload video khác. 

Thời gian để xử lý video là tương đối lâu, video được upload lên được xử lý lần lượt/tuần tự. Có thể theo dõi trạng thái làm việc hiện tại đến bao nhiêu phần trăm, kết quả cuối là hai nhãn violence và non-violence.
