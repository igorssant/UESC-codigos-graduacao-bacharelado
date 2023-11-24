.data
numero1: .float 10.5
numero2: .float 2.5
numero3: .double 5.3
numero4: .double 15.9

.text
lwc1 $f1, numero1
lwc1 $f3, numero2
add.s $f5, $f1, $f3
ldc1 $f2, numero3
ldc1 $f4, numero4
add.d $f6, $f4, $f2
