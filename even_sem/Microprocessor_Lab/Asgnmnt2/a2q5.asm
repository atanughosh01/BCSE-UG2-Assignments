;<Find a given number from a list of given numbers>

JMP Start				; starts the execution

Start:	LXI H, 204FH	; taking count of numbers as input
		MOV D, M		; storing that count in reg D
        LXI H, 204EH	; taking the number to be searched from the list as input
        MOV E, M		; storing that number in reg E
        MVI C, 00H		; initialise position of the number that is to be searched
        LXI H, 2050H	; taking first input of the list of numbers
        
Loop:	MOV A, M		; moving taken list input to accumulator
		CMP E			; compare content of reg E with content of accumulator A
        JNZ Skip		; if numbers in E and A areNotEqual then jump to SKIP
        MOV A, C		; move the positionIndexValue of the number to accumulator A
        STA 204DH		; store that positionIndexValue at address 204DH
        HLT				; halts the execution
        
Skip:	MVI A, FFH		; finalise the position as FFH ifNumberNotMatched 
		STA 204DH		; store that positionIndexValue at address 204DH
        INX H			; increment the inputIndexValue by 1
        INR C			; increment the positionIndexValue by 1
        DCR D			; decrement the count of numbers by 1
        JNZ Loop		; if count of numbers isNotEqualTo 0 jump to LOOP
		HLT				; terminates the execution
        
