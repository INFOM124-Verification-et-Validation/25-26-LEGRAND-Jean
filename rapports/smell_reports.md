### Report 1: Maintenance issues with piece movement logic

**Reported by**: Internal Developer  
**Status**: Open  
**Description**: I spent way too much time fixing a boundary error in the Rook's movement logic, only to realize later that the Bishop had the exact same bug. I had to manually copy-paste the fix and carefully adjust the direction increments in several places. It's frustrating because the core logic is almost identical in both files. I'm worried that if we add more pieces with similar sliding behavior, like a Queen, we'll end up with the same boilerplate code scattered everywhere, making it a nightmare to keep them in sync.

---

### Report 2: Fragile and hard-to-read board setup

**Reported by**: Internal Developer  
**Status**: Open  
**Description**: I tried to swap the starting positions of the King and Queen for a quick test, but I almost broke the whole initialization. The `initializeBoard` method is just a long, repetitive list of coordinate assignments like `boardState[0][3]` and `boardState[7][4]`. One small typo in a number and a piece ends up on the wrong square or accidentally overwrites another one. It's also impossible to see at a glance if all pieces are correctly placed without carefully counting the lines. If we ever want to support different board sizes or variants, we'll have to rewrite this entire method from scratch because it's so tightly bound to specific indices.

---

### Report 3: Difficulty testing move logic in isolation

**Reported by**: Internal Developer  
**Status**: Open  
**Description**: I wanted to write some unit tests to verify complex move sequences, but I discovered that the move validation and execution logic are buried inside the UI controller's click handler. I can't easily test if a move is valid or update the board state without involving the `ChessController` and its dependencies. The `ChessModel` has become a "dumb" data container that doesn't enforce any rules itself. The controller has to manually manage the board state updates, which is error-prone—if we forget to nullify the starting square in one place, we end up with duplicate pieces. This logic should be easier to reach and test independently.
