function calcularSuma(){

// declara variables para obtener lo que el usuario escriba en las cajas de texto 
	const n1 =document.getElementById('num1').value;
	const n2 =document.getElementById('num2').value;


	// enviar datos  n1 y n2 para que se puedan sumar en el Api y me retorne el Resultado
	fetch ('https://suma2numeros-2.onrender.com/sumar',{
		method: 'POST',
		headers:{'Content-Type': 'application/json'},
		body: JSON.stringify({
			
			num1: parseFloat(n1),
			num2:parseFloat(n2)
		})
	})

	.then(response => response.json())
	.then(data=>{
		//Mostrar el resultado en la etiqueta con ID resultado
		document.getElementById('resultado').innerText=data.resultado;
	});
}