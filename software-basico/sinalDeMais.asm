.data
frameBuffer: .space 0x80000 #512 wide x 256 high pixels
m: .word 80
n: .word 40
.text
main:
## clear the display in yellow
la $t0, frameBuffer # load frame buffer addres
li $t1, 0x20000 # save 512*256 pixels
li $t2, 0x00FFFF00 # load yellow color
l1:
sw $t2, 0($t0)
addi $t0, $t0, 4 # advance to next pixel position in display
addi $t1, $t1, -1 # decrement number of pixels
bnez $t1, l1 # repeat while number of pixels is not zero
## draws a cross
## it centers it on the display
li $a0, 0x000000FF # load blue color
lw $a1, m # load size m
lw $a2, n # load size m
li $t0, 256 # x center
li $t1, 128 # y center
move $t2, $a1 # load m in t2
andi $t2, $t2, 1 # test lowest bit to see if it's odd
beqz $t2, skipm # if even, skip
addi $a1, $a1, 1 # increment m by 1
skipm:
move $t2, $a2 # load n in t2
andi $t2, $t2, 1 # test lowest bit to see if it's odd
beqz $t2, skipn # if even, skip
addi $a2, $a2, 1 # increment n by 1
skipn:
srl $t2, $a2, 1 # calculate n/2
## draw top bar
sub $t3, $t1, $t2 # calculate ycenter-n/2
sub $t3, $t3, $a1 # calculate top position = ycenter-n/2 - m
bltz $t3, exit # if negative, we can't draw the cross
sll $t3, $t3, 11 # multiply by 512*4 to get offset in y
sub $t4, $t0, $t2 # calculate xcenter-n/2
sll $t4, $t4, 2 # multiply by 4 to get offset in x
add $t3, $t3, $t4 # add offsets in x and y
la $t4, frameBuffer # load address of buffer
add $t3, $t3, $t4 # get position of first pixel in row
move $t4, $a1 # we will draw m rows
top_row:
move $t5, $a2 # we will draw n columns
move $t6, $t3 # save current row start position
top_col:
sw $a0, 0($t3) # put pixel in current position
addi $t3, $t3, 4 # advance to next pixel
addi $t5, $t5, -1 # decrement number of columns to draw
bnez $t5, top_col # repeat while number of columns is not zero
move $t3, $t6 # get start of row
add $t3, $t3, 2048 # advance to next row
addi $t4, $t4, -1 # decrement number of rows to draw
bnez $t4, top_row # repeat while number of rows is not zero
## draw center bar
sub $t3, $t1, $t2 # calculate ycenter-n/2
sll $t3, $t3, 11 # multiply by 512*4 to get offset in y
sub $t4, $t0, $t2 # calculate xcenter-n/2
sub $t4, $t4, $a1 # calculate xcenter-n/2-m
sll $t4, $t4, 2 # multiply by 4 to get offset in x
add $t3, $t3, $t4 # add offsets in x and y
la $t4, frameBuffer # load address of buffer
add $t3, $t3, $t4 # get position of first pixel in row
move $t4, $a2 # we will draw n rows
center_row:
sll $t5, $a1, 1 # we will draw 2m+n columns
add $t5, $t5, $a2
move $t6, $t3 # save current row start position
center_col:
sw $a0, 0($t3) # put pixel in current position
addi $t3, $t3, 4 # advance to next pixel
addi $t5, $t5, -1 # decrement number of columns to draw
bnez $t5, center_col # repeat while number of columns is not zero
move $t3, $t6 # get start of row
add $t3, $t3, 2048 # advance to next row
addi $t4, $t4, -1 # decrement number of rows to draw
bnez $t4, center_row # repeat while number of rows is not zero
## draw bottom bar
add $t3, $t1, $t2 # calculate ycenter+n/2
sll $t3, $t3, 11 # multiply by 512*4 to get offset in y
sub $t4, $t0, $t2 # calculate xcenter-n/2
sll $t4, $t4, 2 # multiply by 4 to get offset in x
add $t3, $t3, $t4 # add offsets in x and y
la $t4, frameBuffer # load address of buffer
add $t3, $t3, $t4 # get position of first pixel in row
move $t4, $a1 # we will draw m rows
bottom_row:
move $t5, $a2 # we will draw n columns
move $t6, $t3 # save current row start position
bottom_col:
sw $a0, 0($t3) # put pixel in current position
addi $t3, $t3, 4 # advance to next pixel
addi $t5, $t5, -1 # decrement number of columns to draw
bnez $t5, bottom_col # repeat while number of columns is not zero
move $t3, $t6 # get start of row
add $t3, $t3, 2048 # advance to next row
addi $t4, $t4, -1 # decrement number of rows to draw
bnez $t4, bottom_row # repeat while number of rows is not zero
exit:
li $v0, 10 # exit the program
syscall
