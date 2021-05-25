/**
 * Validação de Formulário 
 * @author Leo Ahmed
 */

function validar(){
	//alert("teste")
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	if(nome === ""){
		alert("Preencha o campo nome")
		frmContato.nome.focus()
		return false
	}else if(fone === ""){
		alert("Preencha o campo telefone")
		frmContato.fone.focus()
		return false
	}else{
		document.forms["frmContato"].submit()
	}
}