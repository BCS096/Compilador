        INCLUDE  library/MACRO.X68
        INCLUDE  library/CONST.X68
        INCLUDE  library/VAR.X68
;-----------------------------------------------------------
;-----------------------------------------------------------
; Initial program lines, main                               
;-----------------------------------------------------------
; Labels to memory space reserved for variables:            
;-----------------------------------------------------------
Variable0        DS.L 1
Variable1        DS.L 1
Variable2        DS.L 1
Variable3        DS.L 1
Variable4        DS.L 1
Variable5        DS.L 1
Variable6        DS.L 1
Variable7        DS.L 1
Variable8        DS.L 1
Variable9        DS.L 1
Variable10        DS.L 1
Variable11        DS.L 1
Variable12        DS.L 1
Variable13        DS.L 1
Variable14        DS.L 1
Variable15        DS.L 1
Variable16        DS.L 1
Variable17        DS.L 1
Variable18        DS.L 1
Variable19        DC.B 'Introduce un numero entre 0 y 9: ',0
Variable20        DS.L 1
Variable21        DS.L 1
Variable22        DS.L 1
Variable23        DS.L 1
Variable24        DC.B 'La suma de 1 hasta tu numero es: ',0
Variable25        DS.L 1
Variable26        DS.L 1
Variable27        DS.L 1
;-----------------------------------------------------------
        DS.W 0
;-----------------------------------------------------------
globals:
; Instruction of type: SKIP
;, Store in -> 0

        JMP main ; Declared all globals
Label0:
; Instruction of type: INIT
;, Store in -> 0

        LEA DISP, A0
        MOVE.L 8(A0), -(A7)
        MOVE.L A6, -(A7)
        MOVE.L A7, A6 ;BP = SP
        MOVE.L A6, 8(A7) ; DISP(prof) = BP
        SUB.L #VARSIZE, A7 ; SP changed to assign Variable Space
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 1

        MOVE.L #0, D0 ; Load variable
; Instruction of type: CLONE
;Operator 1 -> 1, Store in -> 2

        MOVE.L 4(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 3

        MOVE.L #1, D0 ; Load variable
        MOVE.L 8(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 4

        MOVE.L 8(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 12(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label1:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 5

        MOVE.L #0, D0 ; Load variable
        MOVE.L 16(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 6

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 20(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #0, D0 ; Load variable
        MOVE.L 24(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 8

        MOVE.L 28(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: IFLE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 0

        CMP.L D1, D0
        BLE Label2
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 9

        MOVE.L #0, D0 ; Load variable
        MOVE.L 8(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label3
; Instruction of type: SKIP
;, Store in -> 0

Label2:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 9

        MOVE.L #1, D0 ; Load variable
        MOVE.L 8(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label3:
; Instruction of type: IFEQ
;Operator 1 -> 9, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 8(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label4
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 10

        MOVE.L #0, D0 ; Load variable
        MOVE.L 36(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 11

        MOVE.L #0, D0 ; Load variable
        MOVE.L 40(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 2, Store in -> 12

        MOVE.L 4(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 44(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #0, D0 ; Load variable
        MOVE.L 48(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 14

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 52(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ADD
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 15

        ADDM D0, D1
        MOVE.L 56(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 15, Store in -> 2

        MOVE.L 56(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 4(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ADD
;Operator 1 -> 4, Operator 2 -> LiteralOp(value: 1), Store in -> 16

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #1, D1 ; Load variable
        ADDM D0, D1
        MOVE.L 60(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 16

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 60(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label1
; Instruction of type: SKIP
;, Store in -> 0

Label4:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 17

        MOVE.L #0, D0 ; Load variable
        MOVE.L 64(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 2, Store in -> 18

        MOVE.L 4(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 68(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: RETURN
;Operator 1 -> 0, Store in -> 0

        MOVE.L A6, A7 ; SP = BP, return to state before PMB
        MOVE.L (A7)+, A6 ; BP = old BP
        LEA DISP, A0 ; A0 = @DISP
        MOVE.L (A7)+, 8(A0) ; DISP[prof] = old value
        RTS ; Return
; Instruction of type: CALL
;Operator 1 -> 0

main:                                                       
        MOVE.L A7, A6
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 19

Variable0        DC.B 'Introduce un numero entre 0 y 9: ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 2304(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 19

        MOVE.L 2304(A6), A0 ; A0 contains store address
        MOVE.L (A0), A0 ; Get parameter
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: READ
;, Store in -> 20

        MOVE.L #5, D0 ; Prepare read
        TRAP #15 ; Expect input
        AND.L #$00FF, D1 ; Mask upper word (we read char = 2 bytes)
        MOVE.L 38(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 21

        MOVE.L #48, D0 ; Load variable
        MOVE.L 80(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 20, Operator 2 -> ReferenceOp(ref: 21), Store in -> 22

        MOVE.L 38(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 80(A6), A0 ; A0 contains store address
        MOVE.L D1, A0
        MOVE.L (A0), D1 ; Get parameter
        SUBM D0, D1
        MOVE.L 84(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 22, Store in -> 23

        MOVE.L 84(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 88(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 24

Variable0        DC.B 'La suma de 1 hasta tu numero es: ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 2944(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 24

        MOVE.L 2944(A6), A0 ; A0 contains store address
        MOVE.L (A0), A0 ; Get parameter
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 25

        MOVE.L #0, D0 ; Load variable
        MOVE.L 96(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 23, Store in -> 26

        MOVE.L 88(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 100(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SIMPLEPARAM
;, Store in -> 0

        MOVE.L 52(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L D0, -(A7)
; Instruction of type: CALL
;Operator 1 -> 0, Store in -> 27

        JSR Label0 ; GOTO Label0
        MOVE.L 104(A6), A0 ; A0 contains parameter @
        MOVE.L D5, (A0) ; Store local parameter
        MOVE.L A6, A7
; Instruction of type: PRINTLN
;Operator 1 -> 27

        MOVE.L 104(A6), A0 ; A0 contains store address
        MOVE.L D1, A0
        MOVE.L (A0), D1 ; Get parameter
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
        MOVE.B #11, D0 ; Next line prepare
        MOVE.L #$00FF, D1 ; Request current coordinates
        TRAP #15 ; Get them in D1.L (HIGH=COL, LOW = ROW)
        AND.L #$00FF, D1 ; We want always column = 0
        ADDQ.B #1, D1 ; We increment the current row by 1
        TRAP #15 ; Set new coordinates (next line ready)
; Instruction of type: RETURN
;Operator 1 -> 0

        SIMHALT
        END globals