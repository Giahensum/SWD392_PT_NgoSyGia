# Prompt để tạo UML Class Diagram cho Hệ thống Booking

## Phiên bản Tiếng Việt

```
Hãy tạo một PlantUML class diagram cho hệ thống đặt phòng khách sạn (hotel booking system) với các yêu cầu sau:

**Kiến trúc phân lớp:**
1. Controller Layer: BookingController
2. Service Layer: IBookingService (interface) và BookingService (implementation)
3. Repository Layer: IBookingRepository (interface)
4. Entity Layer: Hotel, Room, RoomType, Guest, Booking

**Chi tiết từng class:**

**BookingController (Controller):**
- Thuộc tính: bookingService (kiểu IBookingService)
- Phương thức:
  - makeReservation(booking: Booking): void
  - cancelReservation(bookingId: String): void
  - getBookingDetails(bookingId: String): Booking

**IBookingService (Interface):**
- checkAvailability(rooms: List<Room>, in: Date, out: Date): boolean
- calculateTotal(booking: Booking): double
- applyDiscount(booking: Booking): void

**BookingService (Service):**
- Thuộc tính: bookingRepository (kiểu IBookingRepository)
- Implement tất cả phương thức từ IBookingService

**IBookingRepository (Interface):**
- save(booking: Booking): void
- delete(id: String): void
- findById(id: String): Booking
- findAll(): List<Booking>

**Hotel (Entity):**
- Thuộc tính: id, name, address
- Phương thức: addRoom, removeRoom, getRooms

**Room (Entity):**
- Thuộc tính: id, number, price
- Phương thức: isAvailable, getPrice, getType

**RoomType (Entity):**
- Thuộc tính: name, description, basePrice
- Phương thức: getName, getBasePrice

**Guest (Entity):**
- Thuộc tính: id, name, email
- Phương thức: updateInfo, getHistory

**Booking (Entity):**
- Thuộc tính: id, checkInDate, checkOutDate, totalAmount
- Phương thức: addRoom, removeRoom, getInvoice

**Mối quan hệ (Relationships):**
1. BookingController uses/depends on IBookingService (dependency)
2. BookingService implements IBookingService (realization)
3. BookingService uses IBookingRepository (association)
4. Hotel contains Rooms (composition: 1 to many)
5. Room has RoomType (aggregation: many to 1)
6. Guest makes Bookings (association: 1 to many)
7. Booking includes Rooms (aggregation: many to many)
8. IBookingService depends on Booking and Room entities
9. IBookingRepository depends on Booking entity

**Yêu cầu định dạng:**
- Sử dụng PlantUML syntax
- Theme: plain
- Hide empty members
- Linetype: ortho (orthogonal lines)
- Sử dụng stereotypes: <<Controller>>, <<Service>>, <<Interface>>, <<Entity>>
- Hiển thị đầy đủ visibility modifiers (+ cho public, - cho private)
```

---

## English Version

```
Create a PlantUML class diagram for a hotel booking system with the following requirements:

**Layered Architecture:**
1. Controller Layer: BookingController
2. Service Layer: IBookingService (interface) and BookingService (implementation)
3. Repository Layer: IBookingRepository (interface)
4. Entity Layer: Hotel, Room, RoomType, Guest, Booking

**Class Details:**

**BookingController (Controller):**
- Attributes: bookingService (type: IBookingService)
- Methods:
  - makeReservation(booking: Booking): void
  - cancelReservation(bookingId: String): void
  - getBookingDetails(bookingId: String): Booking

**IBookingService (Interface):**
- checkAvailability(rooms: List<Room>, in: Date, out: Date): boolean
- calculateTotal(booking: Booking): double
- applyDiscount(booking: Booking): void

**BookingService (Service):**
- Attributes: bookingRepository (type: IBookingRepository)
- Implements all methods from IBookingService

**IBookingRepository (Interface):**
- save(booking: Booking): void
- delete(id: String): void
- findById(id: String): Booking
- findAll(): List<Booking>

**Hotel (Entity):**
- Attributes: id, name, address
- Methods: addRoom, removeRoom, getRooms

**Room (Entity):**
- Attributes: id, number, price
- Methods: isAvailable, getPrice, getType

**RoomType (Entity):**
- Attributes: name, description, basePrice
- Methods: getName, getBasePrice

**Guest (Entity):**
- Attributes: id, name, email
- Methods: updateInfo, getHistory

**Booking (Entity):**
- Attributes: id, checkInDate, checkOutDate, totalAmount
- Methods: addRoom, removeRoom, getInvoice

**Relationships:**
1. BookingController uses/depends on IBookingService (dependency)
2. BookingService implements IBookingService (realization)
3. BookingService uses IBookingRepository (association)
4. Hotel contains Rooms (composition: 1 to many)
5. Room has RoomType (aggregation: many to 1)
6. Guest makes Bookings (association: 1 to many)
7. Booking includes Rooms (aggregation: many to many)
8. IBookingService depends on Booking and Room entities
9. IBookingRepository depends on Booking entity

**Formatting Requirements:**
- Use PlantUML syntax
- Theme: plain
- Hide empty members
- Linetype: ortho (orthogonal lines)
- Use stereotypes: <<Controller>>, <<Service>>, <<Interface>>, <<Entity>>
- Show full visibility modifiers (+ for public, - for private)
```

---

## Prompt ngắn gọn / Short Prompt


```
Tạo PlantUML class diagram cho hệ thống booking khách sạn theo kiến trúc MVC với:
- Controller: BookingController
- Service: IBookingService, BookingService
- Repository: IBookingRepository
- Entity: Hotel, Room, RoomType, Guest, Booking

Quan hệ chính:
- Hotel composition Room (1-many)
- Room aggregation RoomType (many-1)
- Guest association Booking (1-many)
- Booking aggregation Room (many-many)
- Controller -> Service -> Repository (dependency injection pattern)

Yêu cầu: theme plain, ortho linetype, stereotypes, đầy đủ attributes và methods
```
