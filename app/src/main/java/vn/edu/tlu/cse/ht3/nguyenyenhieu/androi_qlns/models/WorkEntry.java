package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class WorkEntry {
    private long id;
    private String date;
    private String checkInTime;
    private String checkOutTime;
    private String type; // ví dụ: "CheckIn", "CheckOut"

    // Constructor 1: Cho các mục mới (không có ID) - bạn đã có
    public WorkEntry(String date, String checkInTime, String checkOutTime, String type) {
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.type = type;
    }

    // Constructor 2: Cho các mục được truy xuất từ DB (có ID và cả check-in/check-out) - bạn đã có
    public WorkEntry(long id, String date, String checkInTime, String checkOutTime, String type) {
        this.id = id;
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.type = type;
    }

    // --- CONSTRUCTOR CẦN THÊM ĐỂ KHẮC PHỤC LỖI NÀY ---
    // Constructor 3: Cho các mục được truy xuất từ DB, chỉ có một thời gian (check-in HOẶC check-out)
    // Đây là constructor mà DailyWorkScheduleActivity đang cố gắng gọi
    public WorkEntry(long id, String date, String time, String type) {
        this.id = id;
        this.date = date;
        this.type = type;
        if ("CheckIn".equals(type)) {
            this.checkInTime = time;
            this.checkOutTime = null; // Đặt cái còn lại là null
        } else if ("CheckOut".equals(type)) {
            this.checkInTime = null; // Đặt cái còn lại là null
            this.checkOutTime = time;
        } else {
            // Trường hợp loại không xác định hoặc không phải CheckIn/CheckOut rõ ràng
            this.checkInTime = null;
            this.checkOutTime = null;
        }
    }


    // Getters
    public long getId() { return id; }
    public String getDate() { return date; }
    public String getCheckInTime() { return checkInTime; }
    public String getCheckOutTime() { return checkOutTime; }
    public String getType() { return type; }

    // Setters
    public void setId(long id) { this.id = id; }
    public void setDate(String date) { this.date = date; } // Đã sửa lỗi chính tả: this.setDate(date) -> this.date = date;
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }
    public void setType(String type) { this.type = type; }

    // Phương thức getTime() (bạn đã có, tôi giữ nguyên)
    public String getTime() {
        if ("CheckIn".equals(type) && checkInTime != null && !checkInTime.isEmpty()) {
            return checkInTime;
        } else if ("CheckOut".equals(type) && checkOutTime != null && !checkOutTime.isEmpty()) {
            return checkOutTime;
        } else if (checkInTime != null && !checkInTime.isEmpty() && checkOutTime != null && !checkOutTime.isEmpty()) {
            return checkInTime + " - " + checkOutTime;
        } else if (checkInTime != null && !checkInTime.isEmpty()) {
            return checkInTime;
        } else if (checkOutTime != null && !checkOutTime.isEmpty()) {
            return checkOutTime;
        }
        return "";
    }
}