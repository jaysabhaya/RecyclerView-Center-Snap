# CenterSnap RecyclerView

A custom Android Horizontal RecyclerView that intelligently snaps the selected item to the center of the screen, highlights it, and scales down unselected items.

## 📱 Demo Video
<video src="./centersnap_demo.mp4" controls="controls" width="600">
  Your browser does not support the video tag.
</video>

*(Note: If the inline video doesn't auto-play in your Markdown reader, open `centersnap_demo.mp4` directly in your media player).*

## 🧪 Try it out
A pre-built Debug APK is included in the root folder of this repository. You can install it directly to your Android device to test the center-snapping implementation immediately.

👉 **[Download centersnap_demo.apk](./centersnap_demo.apk)**

Or install via terminal:
```bash
adb install centersnap_demo.apk
```

## 💡 What Solution We Created

Standard Android `RecyclerView` does not natively support center-snapping with dynamic scaling and highlighting as you scroll. We created a bespoke implementation combining:
1. `DiscreteScrollView` & `DiscreteScrollLayoutManager`
2. `ScaleTransformer` for slick visual scaling
3. `AdapterAmount` for specialized item rendering

This delivers a seamless, snap-to-center selection experience that handles:
* **Snapping**: Automatically and smoothly scrolls the nearest item to the exact center of the screen when swiping ends.
* **Scaling/Transformation**: Items resize dynamically based on their distance from the center.
* **State Highlighting**: The central item changes appearance (e.g., increased text size, distinct colors) while unselected items remain muted.

## 🎯 Where It Can Be Used

This UI component is perfect for horizontal scrolling experiences that require explicit point-of-focus selection:
* **Amount Selectors** (e.g., picking $10, $20, $50 in payment/donation flows)
* **Time or Date Pickers** (e.g., selecting hours/minutes horizontally)
* **Category Filters / Pills Navigators** (e.g., "All", "News", "Sports")
* **Wheel-like Pickers** formatted securely for modern, flat-UI designs.

## 🚀 How to Easily Integrate Into Your Project

**1. Copy the Core Modules**  
Copy the `customview` and `horizontalcenterselected` packages from this project into your own app's source path.

**2. Add the View to Your XML Layout**  
Add the custom `DiscreteScrollView` to your Activity or Fragment layout:
```xml
<com.yourpackage.customview.DiscreteScrollView
    android:id="@+id/center_scroll_picker"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**3. Setup in Activity / Fragment**  
Bind the data, adapter, and transformer scaling logic in your code:
```kotlin
val scrollPicker = findViewById<DiscreteScrollView>(R.id.center_scroll_picker)
val itemsList = arrayListOf("Item 1", "Item 2", "Item 3")

scrollPicker.setSlideOnFling(true)
scrollPicker.adapter = AdapterAmount(this, itemsList)

// Apply smooth scaling effect for unselected items
scrollPicker.setItemTransformer(
    ScaleTransformer.Builder()
        .setMinScale(0.8f) // Items shrink to 80% when not centered
        .build()
)

// Listen for snapping selection events
scrollPicker.addOnItemChangedListener { viewHolder, position ->
    val selectedItem = itemsList[position]
    // Do something with the selected value!
}
```

**4. That's it!** 🎉  
The component handles all the complicated math required to track screen boundaries, handle fling deceleration, and visually interpolate scales.
