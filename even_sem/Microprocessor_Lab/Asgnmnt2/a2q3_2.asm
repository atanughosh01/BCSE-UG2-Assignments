;<Find the MINIMUM number from a list of numbers>

JMP Start				; starts the execution

Start:	LXI H, 204FH	; taking count of numbers as input
		MOV D, M		; storing that count in reg D
        LXI H, 2050H	; taking first input of the list of numbers
        MOV A, M		; moving taken list input to accumulator
        DCR D			; decrement the count of numbers by 1
        
Loop:   INX H			; increment the inputIndexValue by 1
        CMP M			; compare content of memory M[HL] with content of accumulator A
        JC Skip			; if numIn A < numIn M[HL] jump to SKIP
        MOV A, M		; if numIn A >= numIn M[HL] move content of M to A
        
Skip:	DCR D			; decrement the count of numbers by 1
		JNZ Loop		; if count isNotEqual to 0 jump to LOOP
        STA 204DH		; store the smaller number at memory address 204DH
		HLT				; terminates the execution
		
