# Phần 1: Lý thuyết

## 1. Phân tích sự khác biệt về vòng đời (Lifecycle)

### Giữa Guest và Booking
- **Mối quan hệ**: Association (Liên kết).
- **Phân tích**: Một `Guest` (Khách hàng) tồn tại độc lập với `Booking` (Đơn đặt phòng). Khách hàng có thể tồn tại trong hệ thống mà không cần có đơn đặt phòng nào (ví dụ: vừa đăng ký thành viên). Ngược lại, một `Booking` thường phải gắn liền với một `Guest` cụ thể.
- **Vòng đời**: Nếu `Booking` bị hủy hoặc hoàn thành, `Guest` vẫn tồn tại. Nếu `Guest` bị xóa, tùy vào nghiệp vụ mà `Booking` có thể bị xóa theo hoặc giữ lại (như yêu cầu số 6). Tuy nhiên, về mặt bản chất, `Guest` có vòng đời dài hơn và bao trùm `Booking`.

### Giữa Hotel và Room
- **Mối quan hệ**: Composition (Hợp thành).
- **Phân tích**: `Room` (Phòng) là một bộ phận cấu thành nên `Hotel` (Khách sạn). Phòng không thể tồn tại lơ lửng nếu không thuộc về một khách sạn nào.
- **Vòng đời**: Nếu `Hotel` bị hủy (ngưng hoạt động, xóa khỏi hệ thống), tất cả `Room` thuộc về nó cũng sẽ bị hủy theo. Vòng đời của `Room` phụ thuộc hoàn toàn vào `Hotel`.

## 2. Tại sao lớp BookingService cần thực thi Interface IBookingService?
- **Tính lỏng lẻo (Decoupling)**: `BookingController` chỉ phụ thuộc vào abstraction (`IBookingService`), không phụ thuộc vào implementation cụ thể (`BookingService`). Điều này tuân thủ nguyên lý Dependency Inversion trong SOLID.
- **Dễ dàng kiểm thử (Testing)**: Khi viết Unit Test cho `BookingController`, ta có thể dễ dàng Mock `IBookingService` để giả lập hành vi mà không cần khởi tạo toàn bộ logic nghiệp vụ phức tạp hoặc kết nối database.
- **Linh hoạt thay đổi (Flexibility)**: Nếu sau này cần thay đổi cách xử lý nghiệp vụ (ví dụ: `VipBookingService` hay `HolidayBookingService`), ta chỉ cần tạo class mới implement interface mà không cần sửa code ở Controller.

## 3. Bội số (Multiplicity) giữa Booking và Room
Dựa trên mô tả "Một Booking có thể bao gồm một hoặc nhiều Room":
- Một `Booking` chứa `1..*` (một hoặc nhiều) `Room`.
- Một `Room` có thể xuất hiện trong `0..*` (không hoặc nhiều) `Booking` (theo thời gian khác nhau).

-> **Booking (1..*) ----- (0..*) Room** (hoặc (1..*) ở phía Booking nếu xét tại một thời điểm đặt cụ thể thì Room thuộc về Booking đó, nhưng thường quan hệ này là Many-to-Many nếu xét lịch sử). Trong ngữ cảnh đơn hàng đặt phòng: **Booking (0..*) <---> (1..*) Room**.

## 4. Tham số cho phương thức makeReservation
Trong `BookingController`, phương thức `makeReservation` cần ít nhất các tham số:
1.  `guestId` (String): Định danh khách hàng đặt phòng.
2.  `roomIds` (List\<String\>): Danh sách các phòng muốn đặt.
3.  `dateRange` (Object/Dates): Thời gian đặt (Check-in, Check-out).

## 5. Tương tác trong phương thức calculateTotal
- **Các lớp tương tác**:
    - `Booking`: Lấy thông tin danh sách phòng, ngày check-in, check-out.
    - `Room`: Lấy đơn giá (`price`).
    - `RoomType` (Gián tiếp): Nếu giá phụ thuộc loại phòng.
- **Mũi tên**:
    - `BookingService` ..> `Booking` (Dependency - tham số truyền vào).
    - `BookingService` ..> `Room` (Dependency - truy cập qua Booking).

## 6. Thay đổi yêu cầu: Xóa Guest, giữ Booking cũ
- **Thiết kế hiện tại**: Nếu `Booking` đang tham chiếu cứng (Composition/bắt buộc FK) tới `Guest`, việc xóa `Guest` sẽ lỗi hoặc kéo theo xóa `Booking`.
- **Cần thay đổi?**: **Có**.
- **Giải pháp**: Cơ sở dữ liệu không nên dùng `ON DELETE CASCADE` cho khóa ngoại GuestID trong bảng Booking. Trong code, khi xóa Guest, ta có thể set field `guest` trong `Booking` về `null` hoặc cập nhật trạng thái Guest thành "Archived/Deleted" (Soft Delete) thay vì xóa vật lý khỏi database. Soft Delete là phương án tốt nhất để giữ vẹn toàn dữ liệu lịch sử.

## 7. Interface IBookingRepository và SQL/NoSQL
- Việc tách Interface giúp **ẩn chi tiết triển khai**. Lớp Service chỉ biết gọi `save()`, `find()`, không quan tâm bên dưới dùng JDBC (SQL) hay MongoDriver (NoSQL).
- Khi đổi database, chỉ cần viết một class mới `MongoBookingRepository` implement `IBookingRepository` và thay thế dependency injection. Code của Service và Controller không cần sửa đổi gì.

## 8. Quan hệ Booking - Invoice
- **Yêu cầu**: "Mỗi Booking có ít nhất 1 Invoice. Booking xóa, Invoice giữ lại".
- **Quan hệ**: **Aggregation** (hoặc Association lỏng).
- **Lý do**: Composition đòi hỏi "Parent chết, Child chết theo". Ở đây Booking (Parent) bị xóa nhưng Invoice (Child) vẫn phải sống để báo cáo thuế. Do đó Invoice có vòng đời độc lập tương đối với Booking sau khi được tạo ra.

## 9. Tính năng Online Payment
- **Thực thể Payment**: Thêm vào package `Entities`.
- **Quan hệ Booking - Payment**: **Composition**.
- **Lý do**: Yêu cầu ghi rõ "nếu đơn đặt phòng bị xóa hoàn toàn... các bản ghi thanh toán liên quan cũng không còn giá trị truy xuất độc lập". Điều này thỏa mãn định nghĩa Composition (Strong ownership & collocated lifecycle).
