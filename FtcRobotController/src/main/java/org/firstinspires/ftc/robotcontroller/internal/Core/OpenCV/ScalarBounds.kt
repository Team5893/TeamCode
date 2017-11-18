@file:Suppress("PackageDirectoryMismatch", "PropertyName")
package org.directcurrent.opencv


import org.opencv.core.Scalar


/** Lower HSV bound for the gray glyph */
val GrayGlyphLower = Scalar(0.0 , 0.0 , 0.0)

/** Upper HSV bound for the gray glyph */
val GrayGlyphUpper = Scalar(180.0 , 360.0 , 360.0)

////////////////////////////////////////////////////////////////////////////////////////////////////

/** Lower HSV bound for the brown glyph */
val BrownGlyphLower = Scalar(2.0 , 35.0 , 10.0)

/** Upper HSV bound for the brown glyph */
val BrownGlyphUpper = Scalar(25.0 , 260.0 , 125.0)

////////////////////////////////////////////////////////////////////////////////////////////////////

/** Lower HSV bound for the red jewel */
val RedJewelLower = Scalar(155.0 , 130.0 , 90.0)

/** Upper HSV bound for the red jewel */
val RedJewelUpper = Scalar(180.0 , 255.0 , 255.0)

////////////////////////////////////////////////////////////////////////////////////////////////////

/** Lower HSV bound for the blue jewel */
val BlueJewelLower = Scalar(70.0 , 130.0 , 90.0)

/** Upper HSV bound for the blue jewel */
val BlueJewelUpper = Scalar(120.0 , 255.0 , 255.0)