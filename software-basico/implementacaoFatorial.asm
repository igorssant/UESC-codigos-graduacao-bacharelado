.data
pergunta: .asciiz "Digite um numero inteiro: "
resposta: .asciiz "O resultado e: "

.text

j main

fatorial:
	
	addi $t0, $t0, -1

main:
	la $s1, resposta # carrega a resposta
	la $s0, pergunta # carrega a pergunta
	
	# pergunta
	move $a0, $s0
	li $v0, 4
	syscall
	
	# leitura da pergunta
	li $v0, 5
	syscall
	move $s2, $v0 # <----- salva o conteudo antes de sobreescrever $v0
	
	# chamando a sub-rotina "fatorial"
	move $a0, $s2
	jal fatorial
	
	# salvando a resposta retornada em $s3
	move $s3, $v0
	
	# resposta
	li $v0, 4
	move $a0, $s1
	syscall
	
	# escrita da resposta
	move $a0, $s3 # <----- o conteudo final para ser escrito
	li $v0, 1
	syscall
	
	li $v0, 10
	syscall
	
