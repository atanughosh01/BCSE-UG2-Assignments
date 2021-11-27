;<Copy numbers either starting or ending with 1 to another location>

JMP Start				; starts the execution

Start:	LXI H,2600H		; to set the location where numbers will be stored after execution
	XCHG			; exchange content of H-L reg pair with content of D-E reg pair
	LXI H,2500H		; taking the count of numbers as input
	MOV B,M			; reg B stores the count of numbers

LOOP1:	INX H			; taking the input to form a list of numbers
	MOV A,M			; move that taken input to accumulator A
	RRC			; rotate right accumulator 
	JNC LOOP2		; is D7 isEqualTo 0 jump to LOOP2
	RLC			; rotate left accumulator
	XCHG			; exchange content of H-L reg pair with content of D-E reg pair
	MOV M,A			; move acuumulator content to M [HL]
	INX H			; take the next input of numbers
	XCHG			; exchange content of H-L reg pair with content of D-E reg pair
	JMP SKIP		; unconditionally nump to SKIP
	

LOOP2:	RLC			; rotate left accumulator
	RLC			; rotate left accumulator
	JNC SKIP		; if D0 isNotEqualTo 1 jump to SKIP
	RRC			; rotate right accumulator  if D0 isEqualTo 1
	XCHG			; exchange content of H-L reg pair with content of D-E reg pair
	MOV M,A			; move acuumulator content to M [HL]
	INX H			; taking the input to form a list of numbers
	XCHG			; exchange content of H-L reg pair with content of D-E reg pair

SKIP:	DCR B			; decrementing the count of numbers by 1
	JNZ LOOP		; is count isNotEqualTo 0 jump to LOOP
	HLT			; terminates the execution