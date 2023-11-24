.data
numero1: .float 10.5
numero2: .float 2.5
numero3: .double 5.3
numero4: .double 15.9

.text
# números de precisão simples
lwc1 $f1, numero1
lwc1 $f3, numero2

# subtração simples
div.s $f5, $f1, $f3

# números de precisão dupla
ldc1 $f0, numero3
ldc1 $f2, numero4

# subtração dupla
div.d $f4, $f0, $f2
