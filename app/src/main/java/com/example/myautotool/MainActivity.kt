package com.example.myautotool

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myautotool.ui.theme.MyAutoToolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAutoToolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {
    val context = LocalContext.current

    // Các biến lưu trạng thái form
    var stationCode by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Nền tối (Dark mode)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tiêu đề
                Text(
                    text = "ĐĂNG NHẬP HỆ THỐNG",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00D4FF) // Màu nhấn cyan
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Quản lý bưu cục & tự động hóa",
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(28.dp))

                // 1. Ô nhập Mã bưu cục
                OutlinedTextField(
                    value = stationCode,
                    onValueChange = { stationCode = it },
                    label = { Text("Mã bưu cục (VD: 260000169)") },
                    leadingIcon = { Icon(Icons.Default.Store, contentDescription = null, tint = Color(0xFF00D4FF)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF00D4FF),
                        unfocusedBorderColor = Color(0xFF444444),
                        focusedLabelColor = Color(0xFF00D4FF),
                        unfocusedLabelColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 2. Ô nhập Tên đăng nhập
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Tên đăng nhập") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF00D4FF)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF00D4FF),
                        unfocusedBorderColor = Color(0xFF444444),
                        focusedLabelColor = Color(0xFF00D4FF),
                        unfocusedLabelColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 3. Ô nhập Mật khẩu
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Mật khẩu") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFF00D4FF)) },
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, contentDescription = null, tint = Color.Gray)
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF00D4FF),
                        unfocusedBorderColor = Color(0xFF444444),
                        focusedLabelColor = Color(0xFF00D4FF),
                        unfocusedLabelColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 4. Checkbox Lưu tài khoản
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF00D4FF),
                            uncheckedColor = Color.Gray
                        )
                    )
                    Text(
                        text = "Lưu tài khoản",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 5. Nút Đăng nhập
                Button(
                    onClick = {
                        if (stationCode.isBlank() || username.isBlank() || password.isBlank()) {
                            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Đang đăng nhập bưu cục: $stationCode...", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00D4FF))
                ) {
                    Text(
                        text = "ĐĂNG NHẬP",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
