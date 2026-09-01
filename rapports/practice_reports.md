### Report 4: Mismatch between Javadoc and Implementation (Stale Documentation)

**Reported by**: Internal Developer  
**Status**: Open  
**Description**: I've wasted a significant amount of time debugging the AI strategy because I relied on the Javadoc in `Piece.java`. The documentation explicitly states that `getValidMoves` filters out moves that leave the King in check. However, after tracing a few games where the AI allowed its King to be captured, I realized that none of the concrete implementations (Pawn, Rook, etc.) actually perform this check. It seems the code was updated or simplified at some point, but the documentation was left in a stale state, promising behavior that the implementation no longer provides. This "lying" documentation is worse than no documentation at all.
