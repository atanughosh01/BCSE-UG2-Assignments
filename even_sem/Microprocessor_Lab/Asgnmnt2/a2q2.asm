;<Multiplication of two 8 bit numbers with carry >

JMP Start			; starts the execution

Start: LXI H, 2050H	       ; take the first number as input
	   MOV B, M		; move first number to reg B
       LXI H, 2051H	       ; take the second number as input
       MOV C, M		; move second number to reg C
       
       MVI A, 00H	       ; initialise reg A so that no garbage value resides in it
       MVI D, 00H	       ; initialise the value carry in reg D
       
Loop:  ADD B		       ; add numIn B with numIn accumulator A
	   JNC Skip		; if no carry generated jump to SKIP
       INR D		       ; increment the carry if carry is generated
       
Skip:  DCR C		       ; decrement the second number by 1
	   JNZ Loop		; if result is not 0 jump to LOOP
       STA 2053H	       ; store the number of accumulator at memory address 2053H
       MOV A, D		; move the carry part from reg D to accumulator
       STA 2052H	       ; store the carry of accumulator at memory addess 2052H
	   HLT			; terminates the execution

