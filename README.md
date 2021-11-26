##A.	Cài đặt và deploy
Điều kiện: cài đặt python (và environment nếu muốn)
B1: Clone folder từ git
B2: Cài đặt apache tomcat 8.5, thêm vào eclipse
B3: Thêm sqlite-jdbc-3.7.2.jar vào Classpath của project, thêm sqlite-jdbc-3.7.2.jar vào lib của tomcat 8.5.
B4: Tạo thư mục tên “LTM_webapps” trong ổ C
B4.1: Tạo thư mục “video” trong thư mục “LTM_webapps”
B5: Copy “MyLoginDB SQLite.db” vào thư mục “C:\\LTM_webapps”
B6: Trỏ CMD đến thư mục Python, chạy câu lệnh “pip install -r requirements.txt”
B7: Chạy câu lệnh python myServer.py
** lưu ý: vì vài lý do môi trường một vài thư viện có thể không được cài đầy đủ bởi câu lệnh trên, nên tùy hệ thống sẽ thực hiện pip install riêng với thư viện lỗi.
B8: Chạy Login.jsp bằng run on server.
##B.	Hướng dẫn sử dụng
B1: Đăng nhập với tên abc với pass 123.
B2: Upload một video bất kì với kích thước không quá 100Mb.
-	Bấm browse
-	Chọn file video
-	Bấm submit
B3: Màn hình hiển thị lịch sử xử lý video của hệ thống.
B4: Có thể logout hoặc quay về home page.
