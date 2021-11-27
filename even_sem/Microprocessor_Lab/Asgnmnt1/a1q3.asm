;<Program title>

JMP Start				; Jump to START

Start:	LXI H, 2500H	; Store 25H to H register and 00H to L register
		MOV A, M		; Copy contents of Location 2500H to accumulator
		ANI 0FH			; Perform AND operation of A with 0F and store it to A

		MOV D, A		; Copy the content of accumulator to D register
		MOV A, M		; Copy the content of Memory to accumulator

		RRC				; Rotate right accumulator
		RRC				; Rotate right accumulator
		RRC				; Rotate right accumulator
		RRC				; Rotate right accumulator

		ANI 0FH			; Perform AND operation of A with 0F and store it to A
		ADD D			; Add content of D register with accumulator content
        STA 2550H		; Store contents of accumulator to memory location 2300H
        HLT;			; Terminates the program
