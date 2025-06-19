package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class WorkEntry {
    private long id;
    private String date;
    private String checkInTime;
    private String checkOutTime;
    private String type; // ví dụ: "CheckIn", "CheckOut"

    // Constructor cho các mục mới (không có ID)
    public WorkEntry(String date, String checkInTime, String checkOutTime, String type) {
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.type = type;
    }

    // Constructor cho các mục được truy xuất từ DB (có ID)
    public WorkEntry(long id, String date, String checkInTime, String checkOutTime, String type) {
        this.id = id;
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.type = type;
    }

    // Getters
    public long getId() { return id; }
    public String getDate() { return date; }
    public String getCheckInTime() { return checkInTime; }
    public String getCheckOutTime() { return checkOutTime; }
    public String getType() { return type; }

    // Setters (nếu bạn cần sửa đổi sau khi tạo đối tượng)
    public void setId(long id) { this.id = id; }
    public void setDate(String date) { this.setDate(date); } // Fix: Use setDate
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }
    public void setType(String type) { this.type = type; }

    // --- THÊM PHƯƠNG THỨC NÀY ---
    /**
     * Trả về chuỗi thời gian phù hợp để hiển thị.
     * Ưu tiên thời gian check-in nếu loại là "CheckIn", hoặc check-out nếu loại là "CheckOut".
     * Nếu không, sẽ trả về chuỗi kết hợp hoặc chuỗi rỗng.
     */
    public String getTime() {
        if ("CheckIn".equals(type) && checkInTime != null && !checkInTime.isEmpty()) {
            return checkInTime;
        } else if ("CheckOut".equals(type) && checkOutTime != null && !checkOutTime.isEmpty()) {
            return checkOutTime;
        } else if (checkInTime != null && !checkInTime.isEmpty() && checkOutTime != null && !checkOutTime.isEmpty()) {
            // Trường hợp có cả check-in và check-out (ví dụ: hiển thị một khoảng thời gian)
            return checkInTime + " - " + checkOutTime;
        } else if (checkInTime != null && !checkInTime.isEmpty()) {
            return checkInTime;
        } else if (checkOutTime != null && !checkOutTime.isEmpty()) {
            return checkOutTime;
        }
        return ""; // Mặc định trả về chuỗi rỗng
    }
}