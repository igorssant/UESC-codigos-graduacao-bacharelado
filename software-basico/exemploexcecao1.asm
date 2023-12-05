.data
saida:.asciiz "fui até o final e sai\n"
.text
   li $t0, 50
   li $s0, 5
   sw $s0, ($t0)
   
   li $v0, 4
  la $a0, saida
  syscall