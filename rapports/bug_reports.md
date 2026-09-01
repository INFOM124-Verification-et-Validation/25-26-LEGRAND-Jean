### Bug Report 1: Pawn can move two squares from any rank

**Title**: Pawn double-move available on all ranks  
**Description**: Pawns are able to move two squares forward even after their first move. This violates the rule that the initial two-square advance is only permitted from the starting rank.  
**Steps to Reproduce**:
1. Start a new game.
2. Move a white pawn from e2 to e4 (row 6 to row 4 in internal coordinates).
3. Black makes any move.
4. Attempt to move the white pawn from e4 to e6 (row 4 to row 2).
**Expected Behavior**: The pawn should only be allowed to move one square forward (to e5) as it is no longer on its starting rank.  
**Observed Behavior**: The application allows the pawn to move two squares to e6.

---

### Bug Report 2: Knight moves diagonally and causes game crashes at board edge

**Title**: Knight performs illegal diagonal moves and triggers errors at board boundaries  
**Description**: The Knight piece occasionally moves two squares diagonally (like a Bishop) instead of following its L-shape. Furthermore, the game crashes or freezes when a Knight attempts to move near the bottom or right edges of the board.  
**Steps to Reproduce**:
1. Place a Knight on a central square (e.g., d4).
2. Attempt to move it two squares diagonally (e.g., to f6).
3. Move a Knight to the edge of the board (e.g., rank 7 or file g).
4. Attempt a move that would land on the edge or just outside (e.g., a move that would target a hypothetical "index 8").
**Expected Behavior**: Knights should only move in L-shapes (2x1 or 1x2). All move validation should be strictly bound by the 8x8 board limits (indices 0-7).  
**Observed Behavior**: The Knight is allowed to move to (row+2, col+2). Moves targeting index 8 are permitted by the validation logic, leading to `ArrayIndexOutOfBoundsException`.
