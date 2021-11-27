;<Print N-th Fibonacci number using function>

JMP Start			; starts the execution

Start:	CALL Func	; calls the function
		HLT			; terminates the execution

Func:	LDA 2060H	; take count (N) of number as input
		MOV C,A		; move the count (N) to reg C
		MVI A,00H	; base condition
		DCR C		; decrement the count by 1 (N = N-1)
		JZ Skip		; ifCountEqualsToZero jump to SKIP
		MVI A,01H	; base condition
		DCR C		; decrement the count by 1 (N = N-2)
		JZ Skip		; ifCountEqualsToZero jump to SKIP
     	MVI B,00H	; initialise buffer as 00H
		
Loop:	MOV D,A		; move the sum (Fib(N-1)) to reg D from accumulator A
		ADD B		; calculate the N-th number here (Fib(N) = Fib(N-1) + Fib(N-2))
		MOV B,D		; move previous number of the series (Fib(N-2)) to reg B
		DCR C		; decrement the count by 1
		JNZ Loop	; ifCountNotEqualsToZero, jump to LOOP
	
Skip:   STA 2050H	; store the result at 2050H
		RET			; return the function
		
