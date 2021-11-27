;<Swap nibbles of two 8 bit numbers and store them in specific addresses>

JMP Start			; starts the execution

Start: LXI H, 2050H	       ; take the first number as input
	   MOV A, M		; move first number (MNH) to accumulator A
       LXI H, 2051H  	; take the second number as input
       MOV B, M		; move second number (KLH) to reg B

; calculation for the 2052H position
       ANI 0FH		; AND immediate with 0FH, result 0NH
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator, result N0H
       MOV D, A		; move N0H to reg D
       MOV A, B		; move second number (KLH) to accumulator A
       ANI F0H		; AND immediate with F0H, result K0H
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator, result 0KH
       ADD D		       ; add N0H & 0KH, result NKH
       STA 2052H	       ; store NKH at memory address 2052H

; calculation for the 2053H position
       LDA 2050H	       ; load accumlator with the first number (MNH)
       ANI F0H		; AND immediate with F0H, result M0H
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator, result 0MH
       MOV D, A		; move 0MH to reg D
       MOV A, B		; move second number (KLH) to accumulator A
       ANI 0FH		; AND immediate with 0FH, result 0LH
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator
       RRC			; Rotate right accumulator, result L0H
       ADD D		       ; add L0H & 0MH, result LMH
       STA 2053H	       ; store LMH at memory address 2053H
	   
HLT			    	; terminates the execution
