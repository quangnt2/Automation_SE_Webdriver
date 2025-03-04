# VNHR Profile Manual Test Cases

| Test case ID | Steps | Test data | Expected result |
| --- | --- | --- | --- |
| TC_PROFILE_001 | 1. Mở trang login VNHR. 2. Đăng nhập bằng tài khoản hợp lệ. 3. Truy cập `/profile?lang=vi`. | Email: `Quangnt09176@gmail.com` Password: `Quangnt2` | Màn Profile hiển thị thành công, có tiêu đề `Xin chào`, tab `Thông tin cá nhân` active và form thông tin cá nhân xuất hiện. |
| TC_PROFILE_002 | 1. Vào màn Profile sau đăng nhập. 2. Quan sát menu tab phía trên. | Không có | Hiển thị đủ các tab: Thông tin cá nhân, Thông tin doanh nghiệp, Lời mời doanh nghiệp, Sự kiện đặt vé, Mã giảm giá, Lịch sử đơn hàng, Danh sách tài liệu đã tải, Thông báo, Đăng xuất. |
| TC_PROFILE_003 | 1. Vào màn Profile sau đăng nhập. 2. Kiểm tra nhóm `Tài khoản của tôi`. | Không có | Các field Họ và tên, Số điện thoại, Email đăng nhập, Mật khẩu đăng nhập, Ngày sinh, Giới tính, Địa chỉ hiển thị. Họ tên và email có dữ liệu. |
| TC_PROFILE_004 | 1. Vào màn Profile sau đăng nhập. 2. Kiểm tra field Email đăng nhập và Mật khẩu đăng nhập. | Không có | Email đăng nhập ở trạng thái readonly. Mật khẩu đăng nhập ở trạng thái readonly, type là `password`, không hiển thị trực tiếp mật khẩu thật. |
| TC_PROFILE_005 | 1. Vào màn Profile sau đăng nhập. 2. Kiểm tra các nhóm thông tin còn lại trên form. | Không có | Hiển thị các nhóm Thông tin cá nhân, Thông tin doanh nghiệp, Về VNHR, kèm các field chính: nơi công tác, kinh nghiệm, công ty, cấp bậc, quy mô, biết VNHR từ đâu, lý do tham gia. |
| TC_PROFILE_006 | 1. Vào màn Profile sau đăng nhập. 2. Xóa dữ liệu Họ và tên. 3. Nhấn Cập nhật. | Họ và tên: trống | Không cập nhật. Hiển thị lỗi `Họ và tên không được để trống`. |
| TC_PROFILE_007 | 1. Vào màn Profile sau đăng nhập. 2. Nhập chữ vào Số điện thoại. 3. Nhấn Cập nhật. | Số điện thoại: `abc` | Không cập nhật. Hiển thị lỗi `Số điện thoại không hợp lệ`. |
| TC_PROFILE_008 | 1. Vào màn Profile sau đăng nhập. 2. Nhấn nút Gia hạn. | Không có | Điều hướng đến chức năng gia hạn membership `/profile/join-membership`. |
| TC_PROFILE_009 | 1. Vào màn Profile sau đăng nhập. 2. Nhấn tab Đăng xuất. | Không có | Người dùng được đăng xuất, không còn thấy trạng thái đã đăng nhập hoặc link logout. |
