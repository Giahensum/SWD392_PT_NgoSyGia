# Prompt để tạo UML Class Diagram cho Hệ thống Booking

## Phiên bản Tiếng Việt

```
Hãy tạo một PlantUML class diagram cho hệ thống đặt phòng khách sạn (hotel booking system) với các yêu cầu sau:

**Kiến trúc phân lớp:**
1. Controller Layer: BookingController
2. Service Layer: IBookingService (interface), BookingService (implementation)
3. Repository Layer: IBookingRepository, BookingRepository, IRoomRepository (interfaces & implementations)
4. Entity Layer: Hotel, Room, RoomType, Guest, Booking, Invoice, Payment

**Chi tiết từng class:**

**BookingController (Controller):**
- Thuộc tính: 
  - bookingService: IBookingService
  - logger: Logger
  - validator: RequestValidator
- Phương thức:
  - makeReservation(guestId: String, roomIds: List<String>, checkIn: Date, checkOut: Date): Booking
  - cancelReservation(bookingId: String): boolean
  - getBookingDetails(bookingId: String): Booking
  - getAllBookings(): List<Booking>

**IBookingService (Interface):**
- checkAvailability(rooms: List<Room>, checkIn: Date, checkOut: Date): boolean
- calculateTotal(booking: Booking): double
- applyDiscount(booking: Booking, discountCode: String): void
- processPayment(booking: Booking, paymentMethod: String): Payment

**BookingService (Service):**
- Thuộc tính: 
  - bookingRepository: IBookingRepository
  - roomRepository: IRoomRepository
  - discountCalculator: DiscountCalculator
- Phương thức (implement IBookingService + thêm):
  - checkAvailability(rooms: List<Room>, checkIn: Date, checkOut: Date): boolean
  - calculateTotal(booking: Booking): double
  - applyDiscount(booking: Booking, discountCode: String): void
  - processPayment(booking: Booking, paymentMethod: String): Payment
  - createBooking(guest: Guest, rooms: List<Room>, checkIn: Date, checkOut: Date): Booking

**IBookingRepository (Interface):**
- save(booking: Booking): void
- delete(id: String): void
- findById(id: String): Booking
- findAll(): List<Booking>
- findByGuestId(guestId: String): List<Booking>

**BookingRepository (Repository):**
- Thuộc tính:
  - database: DatabaseConnection
  - queryBuilder: QueryBuilder
  - cache: CacheManager
- Phương thức (implement IBookingRepository):
  - save(booking: Booking): void
  - delete(id: String): void
  - findById(id: String): Booking
  - findAll(): List<Booking>
  - findByGuestId(guestId: String): List<Booking>

**IRoomRepository (Interface):**
- save(room: Room): void
- findById(id: String): Room
- findAvailableRooms(checkIn: Date, checkOut: Date): List<Room>

**Hotel (Entity):**
- Thuộc tính: id, name, address, phone, rating
- Phương thức: addRoom, removeRoom, getRooms, updateInfo

**Room (Entity):**
- Thuộc tính: id, roomNumber, floor, price, status
- Phương thức: isAvailable, getPrice, getRoomType, updateStatus

**RoomType (Entity):**
- Thuộc tính: id, name, description, basePrice, capacity
- Phương thức: getName, getBasePrice, getCapacity, updatePrice

**Guest (Entity):**
- Thuộc tính: id, firstName, lastName, email, phone
- Phương thức: updateInfo, getBookingHistory, getFullName, validateContact

**Booking (Entity):**
- Thuộc tính: id, checkInDate, checkOutDate, totalAmount, status, discountApplied
- Phương thức: addRoom, removeRoom, calculateDuration, getInvoice, updateStatus

**Invoice (Entity):**
- Thuộc tính: id, invoiceNumber, issueDate, totalAmount, taxAmount, status
- Phương thức: generateInvoiceNumber, calculateTax, printInvoice, markAsPaid

**Payment (Entity):**
- Thuộc tính: id, paymentMethod, amount, transactionId, paymentDate, status
- Phương thức: processTransaction, refund, getReceipt, validatePayment

**Mối quan hệ (Relationships):**

Dependency:
1. BookingController ..> IBookingService (uses)
2. BookingService ..> IRoomRepository (uses)
3. BookingService ..> Booking, Room, Guest, Payment (depends)
4. IBookingRepository ..> Booking (manages)
5. IRoomRepository ..> Room (manages)

Realization:
6. BookingService <|.. IBookingService (implements)
7. BookingRepository <|.. IBookingRepository (implements)

Association:
8. BookingService --> IBookingRepository (uses)
9. Guest "1" --> "0..*" Booking (makes)

Composition:
10. Hotel "1" *-- "0..*" Room (owns) - Room không tồn tại độc lập khi Hotel bị xóa
11. Booking "1" *-- "0..*" Payment (processes) - Payment không có giá trị khi Booking bị xóa

Aggregation:
12. Room "0..*" o-- "1" RoomType (categorized by) - RoomType tồn tại độc lập
13. Booking "0..*" o-- "1..*" Room (reserves) - Room tồn tại độc lập
14. Booking "1" o-- "0..1" Invoice (generates) - Invoice phải giữ lại cho báo cáo thuế

**Yêu cầu định dạng:**
- Sử dụng PlantUML syntax
- Theme: plain
- Linetype: ortho (orthogonal lines)
- Sử dụng stereotypes: <<Controller>>, <<Service>>, <<Repository>>, <<Interface>>, <<Entity>>
- Hiển thị đầy đủ visibility modifiers (+ cho public, - cho private)
- Hiển thị đầy đủ multiplicity cho tất cả quan hệ entity (1, 0..1, 0..*, 1..*)
- Mỗi class phải có ít nhất 3 attributes và 2 methods
```

---

## English Version

```
Create a PlantUML class diagram for a hotel booking system with the following requirements:

**Layered Architecture:**
1. Controller Layer: BookingController
2. Service Layer: IBookingService (interface), BookingService (implementation)
3. Repository Layer: IBookingRepository, BookingRepository, IRoomRepository (interfaces & implementations)
4. Entity Layer: Hotel, Room, RoomType, Guest, Booking, Invoice, Payment

**Class Details:**

**BookingController (Controller):**
- Attributes: 
  - bookingService: IBookingService
  - logger: Logger
  - validator: RequestValidator
- Methods:
  - makeReservation(guestId: String, roomIds: List<String>, checkIn: Date, checkOut: Date): Booking
  - cancelReservation(bookingId: String): boolean
  - getBookingDetails(bookingId: String): Booking
  - getAllBookings(): List<Booking>

**IBookingService (Interface):**
- checkAvailability(rooms: List<Room>, checkIn: Date, checkOut: Date): boolean
- calculateTotal(booking: Booking): double
- applyDiscount(booking: Booking, discountCode: String): void
- processPayment(booking: Booking, paymentMethod: String): Payment

**BookingService (Service):**
- Attributes: 
  - bookingRepository: IBookingRepository
  - roomRepository: IRoomRepository
  - discountCalculator: DiscountCalculator
- Methods (implement IBookingService + additional):
  - checkAvailability(rooms: List<Room>, checkIn: Date, checkOut: Date): boolean
  - calculateTotal(booking: Booking): double
  - applyDiscount(booking: Booking, discountCode: String): void
  - processPayment(booking: Booking, paymentMethod: String): Payment
  - createBooking(guest: Guest, rooms: List<Room>, checkIn: Date, checkOut: Date): Booking

**IBookingRepository (Interface):**
- save(booking: Booking): void
- delete(id: String): void
- findById(id: String): Booking
- findAll(): List<Booking>
- findByGuestId(guestId: String): List<Booking>

**BookingRepository (Repository):**
- Attributes:
  - database: DatabaseConnection
  - queryBuilder: QueryBuilder
  - cache: CacheManager
- Methods (implement IBookingRepository):
  - save(booking: Booking): void
  - delete(id: String): void
  - findById(id: String): Booking
  - findAll(): List<Booking>
  - findByGuestId(guestId: String): List<Booking>

**IRoomRepository (Interface):**
- save(room: Room): void
- findById(id: String): Room
- findAvailableRooms(checkIn: Date, checkOut: Date): List<Room>

**Hotel (Entity):**
- Attributes: id, name, address, phone, rating
- Methods: addRoom, removeRoom, getRooms, updateInfo

**Room (Entity):**
- Attributes: id, roomNumber, floor, price, status
- Methods: isAvailable, getPrice, getRoomType, updateStatus

**RoomType (Entity):**
- Attributes: id, name, description, basePrice, capacity
- Methods: getName, getBasePrice, getCapacity, updatePrice

**Guest (Entity):**
- Attributes: id, firstName, lastName, email, phone
- Methods: updateInfo, getBookingHistory, getFullName, validateContact

**Booking (Entity):**
- Attributes: id, checkInDate, checkOutDate, totalAmount, status, discountApplied
- Methods: addRoom, removeRoom, calculateDuration, getInvoice, updateStatus

**Invoice (Entity):**
- Attributes: id, invoiceNumber, issueDate, totalAmount, taxAmount, status
- Methods: generateInvoiceNumber, calculateTax, printInvoice, markAsPaid

**Payment (Entity):**
- Attributes: id, paymentMethod, amount, transactionId, paymentDate, status
- Methods: processTransaction, refund, getReceipt, validatePayment

**Relationships:**

Dependency:
1. BookingController ..> IBookingService (uses)
2. BookingService ..> IRoomRepository (uses)
3. BookingService ..> Booking, Room, Guest, Payment (depends)
4. IBookingRepository ..> Booking (manages)
5. IRoomRepository ..> Room (manages)

Realization:
6. BookingService <|.. IBookingService (implements)
7. BookingRepository <|.. IBookingRepository (implements)

Association:
8. BookingService --> IBookingRepository (uses)
9. Guest "1" --> "0..*" Booking (makes)

Composition:
10. Hotel "1" *-- "0..*" Room (owns) - Room cannot exist without Hotel
11. Booking "1" *-- "0..*" Payment (processes) - Payment has no value when Booking deleted

Aggregation:
12. Room "0..*" o-- "1" RoomType (categorized by) - RoomType exists independently
13. Booking "0..*" o-- "1..*" Room (reserves) - Room exists independently
14. Booking "1" o-- "0..1" Invoice (generates) - Invoice must be kept for tax reports

**Formatting Requirements:**
- Use PlantUML syntax
- Theme: plain
- Linetype: ortho (orthogonal lines)
- Use stereotypes: <<Controller>>, <<Service>>, <<Repository>>, <<Interface>>, <<Entity>>
- Show full visibility modifiers (+ for public, - for private)
- Show full multiplicity for all entity relationships (1, 0..1, 0..*, 1..*)
- Each class must have at least 3 attributes and 2 methods
```

---

## Prompt ngắn gọn / Short Prompt

```
Tạo PlantUML class diagram cho hệ thống booking khách sạn theo kiến trúc phân lớp với:

**Layers:**
- Controller: BookingController
- Service: IBookingService, BookingService  
- Repository: IBookingRepository, BookingRepository, IRoomRepository
- Entity: Hotel, Room, RoomType, Guest, Booking, Invoice, Payment

**Quan hệ chính:**
- Hotel *-- Room (Composition: 1 to 0..*)
- Room o-- RoomType (Aggregation: 0..* to 1)
- Guest --> Booking (Association: 1 to 0..*)
- Booking o-- Room (Aggregation: 0..* to 1..*)
- Booking o-- Invoice (Aggregation: 1 to 0..1) - giữ lại cho thuế
- Booking *-- Payment (Composition: 1 to 0..*) - xóa cùng booking
- Controller ..> Service, Service ..> Repository (Dependency)
- Service <|.. Implementation (Realization)

**Yêu cầu:**
- Theme: plain, linetype: ortho
- Stereotypes: <<Controller>>, <<Service>>, <<Repository>>, <<Interface>>, <<Entity>>
- Visibility: +/- cho tất cả members
- Mỗi class: ít nhất 3 attributes, 2 methods
- Multiplicity đầy đủ cho Entity relationships
```

