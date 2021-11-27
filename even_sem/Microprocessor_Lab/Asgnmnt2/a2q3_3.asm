;<Sort given N numbers in ascending order>

JMP Start				; starts the execution

Start:	LXI H, 204FH	; taking count of numbers as input
		MOV C, M		; storing that count in reg C
     	DCR C			; decrement the count of numbers by 1
		JZ SKIP1		; if no number is present in the list jump to SKIP1
        
LOOP1:	MOV B, C		; moving the count to reg B
		LXI H,2050H		; taking first input of the list of numbers
        
LOOP2:	MOV A, M		; moving current list input to accumulator
		INX H			; increment inputIndexValue by 1 to take next input
		MOV E, M		; moving the next taken input to reg E
		CMP E			; comparing num [i] with num [i + 1]
		JC SKIP2		; if num [i] < num [i+1] jump to SKIP2
		DCX H			; if num [i] >= num [i+1] return to previous index
		MOV M, E		; store the (i+1)-th input at i-th position
		INX H			; increment inputIndexValue by 1
		MOV M, A		; store the i-th input at (i+1)-th position // swapping complete
        
SKIP2:	DCR B			; decrement the max index of input array by 1
		JNZ LOOP2		; if max index isNotEqualTo 0 jump to LOOP2
		DCR C			; decrement the count of numbers by 1
		JNZ LOOP1		; if count isNotEqualTo 0 jump to LOOP1
          	
SKIP1:	HLT 			; terminates the execution

