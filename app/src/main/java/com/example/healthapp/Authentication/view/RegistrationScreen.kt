package com.example.healthapp.Authentication.view

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.healthapp.Authentication.ViewModel.AuthState
import com.example.healthapp.Authentication.ViewModel.MobileAuthViewModel
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R


@SuppressLint("ContextCastToActivity")
@Composable
fun RegistrationScreen(navController: NavHostController){

    val phoneAuthViewModel: MobileAuthViewModel = viewModel()

    val authState by phoneAuthViewModel.authState.collectAsState()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity



    var expanded by remember{
        mutableStateOf(false)
    }

    var selectCountry by remember{
        mutableStateOf("Madhya Pradesh")
    }

    var otp by remember { mutableStateOf("") }

    var verificationId by remember { mutableStateOf<String?>(null) }
    var countryCode by remember {
        mutableStateOf("+91")
    }

    var mobileNumber by remember {
        mutableStateOf("")
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(colorResource(R.color.lightBlue)), horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(modifier = Modifier.height(126.dp))
        Text("Enter You Mobile Number",
            fontSize = 20.sp,
            color = colorResource(id = R.color.teal_700),
            fontWeight = FontWeight.Bold
        )



        Spacer(modifier = Modifier.height(26.dp))

        Row {

            Text(text = "This App Need to verify Your Number ")

            Spacer(modifier = Modifier.width(4.dp))

            Text(text = "Service` s ", color = colorResource(id = R.color.teal_700))


        }

        Spacer(modifier = Modifier.height(26.dp))


        TextButton(onClick = {expanded = true}, modifier = Modifier.fillMaxWidth()) {

            Box(modifier = Modifier.width(230.dp)){

                Text(text = selectCountry,
                    modifier = Modifier.align(Alignment.Center), fontSize = 16.sp,  color = Color.Black
                )

                Icon(imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                , tint = colorResource(id = R.color.teal_700
                    ))
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 66.dp), thickness = 2.dp, color = colorResource(id = R.color.teal_700)
        )

        DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {

            listOf("India").forEach{ country ->
                DropdownMenuItem(text = { Text(text = country) }, onClick = {
                    selectCountry = country
                    expanded = false
                })

            }
        }


        when(authState){

            is AuthState.Ideal, is AuthState.Loading, is AuthState.CodeSent ->{

                if(authState is AuthState.CodeSent){
                    verificationId = (authState as AuthState.CodeSent).verificationId
                }

                if(verificationId == null){

                    Spacer(modifier = Modifier.width(16.dp))

                    Row{
                        TextField(
                            value =  countryCode,
                            onValueChange = {
                                countryCode = it
                            },
                            modifier = Modifier.width(70.dp),
                            singleLine = true,
                            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor= Color.Transparent,

                                unfocusedIndicatorColor = colorResource(id = R.color.teal_700),
                                focusedIndicatorColor = colorResource(id = R.color.teal_700)
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                        TextField(
                            value = mobileNumber,
                            onValueChange = {

                                mobileNumber = it
                            },
                            singleLine = true,
                            placeholder = { Text(text = "Enter Phone Number") },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor= Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            )
                        )

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        if(mobileNumber.isNotEmpty()){
                            val fullMobileNumber = "$countryCode$mobileNumber"

                            phoneAuthViewModel.sendVerificationCode(fullMobileNumber,activity)
                        }else{

                            Toast.makeText(context,"Please Enter a valid Phone Number",Toast.LENGTH_SHORT).show()
                        }
                    }, shape = RoundedCornerShape(6.dp), colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700))   ) {

                        Text(text = "Send OTP", fontSize = 16.sp)
                    }

                    if(authState is AuthState.Loading){

                        Spacer(modifier = Modifier.height(26.dp))

                        CircularProgressIndicator()
                    }


                }else{
                    // OTP Input section

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = otp,
                        onValueChange = {

                            otp = it
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Enter OTP") },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor= Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(34.dp))

                    Button(onClick = {
                        if(otp.isNotEmpty() && verificationId != null){

                            phoneAuthViewModel.verifyCode(otp,context)
//                            navController.navigate(Routes.Profile)
                        }else{

                            Toast.makeText(context,"Please Enter a validOTP",Toast.LENGTH_SHORT).show()

                        }
                    }, shape = RoundedCornerShape(6.dp), colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700))   ){

                        Text(text="Verify OTP")
                    }

                    if (authState is AuthState.Loading){
                        Spacer(modifier = Modifier.height(16.dp))

                        CircularProgressIndicator()
                    }

                }
            }

            is AuthState.Success -> {

                Log.d("PhoneAuth","LoginSuccessFull")

                phoneAuthViewModel.resetAuthState()

                navController.navigate(Routes.Profile){
                    popUpTo<Routes.RegistrationScreen>{
                        inclusive = true
                    }
                }
            }
            is AuthState.Error -> {

                Toast.makeText(context,(authState as AuthState.Error).message,Toast.LENGTH_SHORT).show()
            }

        }


//


    }


}