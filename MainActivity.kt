import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SumaScreen()
        }
    }
}

@Composable
fun SumaScreen() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("El resultado aparecerá aquí") }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = num1, onValueChange = { num1 = it }, label = { Text("Número 1") })
        TextField(value = num2, onValueChange = { num2 = it }, label = { Text("Número 2") })
        
        Button(onClick = {
            scope.launch {
                try {
                    val response = RetrofitClient.instance.sumarNumeros(
                        SumaRequest(num1.toInt(), num2.toInt())
                    )
                    resultado = "Resultado: ${response.resultado}"
                } catch (e: Exception) {
                    resultado = "Error: ${e.message}"
                }
            }
        }) {
            Text("Sumar")
        }
        Text(text = resultado, modifier = Modifier.padding(top = 16.dp))
    }
}