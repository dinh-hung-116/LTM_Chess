# LTM-Chess
Đồ án lập trình mạng
Hùng là GAYYY!!!!!

ĐỀ TÀI 2: XÂY DỰNG CHƯƠNG TRÌNH CHƠI CỜ 
Yêu cầu về chức năng phía client (phải có GUI): 
▪ Nhóm tự chọn loại cờ, ngoại trừ cờ caro. 
▪ Đăng ký tài khoản mới, đăng nhập, cập nhật thông tin tài khoản tương tự đề tài 1 (loại trừ phần xác thực email). 
▪ Chức năng chơi game:  
✓ Cứ một cặp user đăng nhập sẽ được ghép đôi thành 1 phiên chơi sau khi có xác nhận  đồng ý từ cả hai user. 
✓ Nếu 1 user đăng nhập vào trường hợp lẻ, user sẽ được đưa vào hàng đợi và có thể chọn  xem 1 phiên chơi của một cặp bất kỳ. User này sẽ tự động được ghép đôi tạo thành 1  phiên chơi mới khi có một user nữa đăng nhập (cần có xác nhận đồng ý từ cả hai user).  Trong trường hợp một trong hai user không đồng ý ghép đôi, cả hai sẽ được đưa lại vào  hàng đợi và user từ chối ghép đôi sẽ bị trừ 1 điểm thành tích. 
✓ Mỗi phiên chơi giới hạn trong 10 phút, nếu hết thời gian thì xem như 2 user hòa. Thời  gian giới hạn cho mỗi nước đi là 30 giây, user nào không đi nước mới trong thời gian  giới hạn sẽ bị xử thua. Điểm phiên chơi được tính theo tỉ lệ (gợi ý): 
▪ Thắng: cộng 3 điểm. 
▪ Hòa: cộng 1 điểm. 
▪ Thua: trừ 1 điểm. 
▪ Chat nhóm trong khi chơi: giữa người chơi 1 – người chơi 2 – người xem. ▪ Xem thành tích cá nhân: số trận thắng/thua, tỉ lệ thắng, chuỗi trận thắng/thua dài nhất, điểm  thành tích (nhóm tự xác định công thức tính điểm thành tích sao cho hợp lý). ▪ Xếp hạng: liệt kê thứ hạng tất cả các user đã tham gia dựa trên điểm thành tích; chuỗi trận  thắng; tỉ lệ thắng (cần quan tâm đến trường hợp user mới tham gia vài trận). 
Yêu cầu về chức năng phía server (không cần GUI): 
▪ Thống kê được tổng số user, số user đang online. 
▪ Block một user khỏi hệ thống. 
▪ Ghi log các trận đấu (các user tham gia, thời gian chơi, thời gian bắt đầu, ….) và query được các  thông tin cơ bản (user thắng nhiều nhất, trận đấu ngắn nhất…). 
Yêu cầu chung: 
▪ Mã hóa nội dung trao đổi giữa client – server. Phải sử dụng key khác nhau cho các client. ▪ Các client phải chạy trên các máy tính khác nhau.
