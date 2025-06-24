package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Ánh xạ các thành phần UI
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Đặt lắng nghe sự kiện cho nút Đăng nhập
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Kiểm tra xem các trường nhập liệu có rỗng không
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Vui lòng nhập tên đăng nhập.");
            editTextUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Vui lòng nhập mật khẩu.");
            editTextPassword.requestFocus();
            return;
        }

        // Gọi phương thức checkUser từ DatabaseHelper để xác thực
        String role = dbHelper.checkUser(username, password);

        if (role != null) {
            // Đăng nhập thành công, kiểm tra vai trò để chuyển hướng
            if ("admin".equals(role)) {
                Toast.makeText(MainActivity.this, "Đăng nhập thành công! Chào mừng Admin.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
                finish(); // Đóng MainActivity để người dùng không quay lại màn hình đăng nhập
            } else if ("employee".equals(role)) {
                Toast.makeText(MainActivity.this, "Đăng nhập thành công! Chào mừng Nhân viên.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EmployeeActivity.class);
                startActivity(intent);
                finish(); // Đóng MainActivity
            } else {
                // Trường hợp vai trò không xác định (rất hiếm nếu dữ liệu được kiểm soát chặt)
                Toast.makeText(MainActivity.this, "Vai trò người dùng không hợp lệ.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Đăng nhập thất bại (sai tên đăng nhập hoặc mật khẩu)
            Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng.", Toast.LENGTH_SHORT).show();
        }
    }
}