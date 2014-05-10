kairos
======

A Java-based interpreter for Whitespace and WhitespaceAssembler



Part 1: WhitespaceAssembler command overview
------

**Stack** operations

'push X'        |push value X onto stack
'dupl'          |duplicate value on top of stack
'copy X'        |copy X-th value onto stack
'swap'          |swap top two items of stack
'pop'           |discard top item from stack
'slide X'       |discard X items after top item from stack


**Arithmetic** operations (*Note*: Removes top two values from stack, places result of operation on top.)

'add'           |add top two items
'sub'           |subtract top item from second to top item
'mul'           |multiply top two items
'div'           |divide second to top item by top item
'mod'           |modulo compute remainder of the division operation of top two items
        

**Heap** operations (*Note*: Requires address on top of stack, and a value on top of that in case of 'store'. Consumes both, if applicable; places retrieved value on top of stack in case of 'get'.)

'store X'       |store value X in heap
'get'           |get value from heap


**Flow control** operations

'label X'       |create label X at this point
'call X'        |call subroutine X
'jump X'        |jump to label X
'jzero X'       |jump to label X if top item on stack is zero
'jneg X'        |jump to label X if top item on stack is negative
'ret'           |return from subroutine
'exit'          |exit program


**Input/Output** operations

'printc'        |print character
'printn'        |print number
'readc'         |read character
'readn'         |read number


**Meta** operations (*Note*: These are not part of the Whitespace specification; they currently can only be resolved by the Kairos interpreter and will be lost in translation to Whitespace proper.)

'import X'      |import WhitespaceAssembler function X into current code; duplicate imports have no effect.







