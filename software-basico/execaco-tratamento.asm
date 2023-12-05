.text
   li $t0, 4
   li $s0, 5
   sw $s0, ($t0)  #excepçao de endereço errado
   addi $s2, $s0, -2
   move $a0, $s2
   li     $v0, 1 
   syscall 
   li $v0, 10
   syscall
   
   
   # Exception Handler starts here
   .ktext  0x80000180 
   move   $k0, $at # $k0 = $at 
   la     $k1, _regs # $k1 = address of _regs 
   sw     $k0, 0($k1) # save $at 
   sw     $v0, 4($k1) # save $v0 
   sw     $a0, 8($k1) # save $a0 
   la     $a0, _msg1 # $a0 = address of _msg1 
   li     $v0, 4 # $v0 = service 4 
   syscall # Print _msg1 
   mfc0   $a0, $14 # $a0 = EPC 
   li     $v0, 34 # $v0 = service 34
    syscall # print EPC in hexadecimal 
    la     $a0, _msg2 # $a0 = address of _msg2 
    li     $v0, 4 # $v0 = service 4 
    syscall # Print _msg2 
    mfc0   $a0, $13 # $a0 = cause 
    srl    $a0, $a0, 2 # shift right by 2 bits 
    andi   $a0, $a0, 31 # $a0 = exception code 
    li     $v0, 1 # $v0 = service 1 
    syscall # Print exception code 
    la     $a0, _msg3 # $a0 = address of _msg3 
    li     $v0, 4 # $v0 = service 4 
    syscall # Print _msg3 
    la     $k1, _regs # $k1 = address of _regs
     lw     $at, 0($k1) # restore $at
      lw     $v0, 4($k1) # restore $v0 
      lw     $a0, 8($k1) # restore $a0 
      mtc0   $zero, $8 # clear vaddr 
      mfc0   $k0, $14 # $k0 = EPC 
      addiu  $k0, $k0, 4 # Increment $k0 by 4 
      mtc0   $k0, $14 # EPC = point to next instruction 
      eret    # exception return: PC = EPC
       # kernel data is stored here 
       .kdata 
       _msg1: .asciiz   "\nException causada pelo endereço: " 
       _msg2: .asciiz   "\nException Code = " 
       _msg3: .asciiz   "\nIgnorar e continuar o programa ...\n" 
       _regs: .word 0:3 # Space for saved registers
