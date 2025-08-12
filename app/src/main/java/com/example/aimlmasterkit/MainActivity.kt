package com.example.aimlmasterkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Data Model ---
/**
 * A simple data class to represent a course.
 * In a real app, this might come from a local database or a remote API.
 */
data class Course(
    val id: String,
    val title: String,
    val description: String
)

// --- Sample Data ---
/**
 * A hardcoded list of courses for demonstration purposes.
 * This replaces the need for Firestore in this simplified version.
 */
val sampleCourses = listOf(
    Course("python_basics", "Python Basics", "Learn the fundamentals of the Python language."),
    Course("intro_ml", "Intro to Machine Learning", "Discover the core concepts of ML."),
    Course("neural_networks", "Neural Networks", "Understand the building blocks of deep learning."),
    Course("robotics_101", "Robotics 101", "An introduction to the world of robotics and automation.")
)

// --- Main Activity (App Entry Point) ---
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // The Root composable that sets up the theme.
            // In a real project, you would define your theme in a separate Theme.kt file.
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Color(0xFF00FF7F), // Spring Green
                    background = Color(0xFF121212), // Almost Black
                    onBackground = Color.White,
                    surface = Color(0xFF1E1E1E), // Dark Grey for cards
                    onSurface = Color.White
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

// --- Main App Composable ---
@Composable
fun App() {
    // Since we removed authentication and multiple screens, the structure is much simpler.
    // The Scaffold provides a basic layout structure.
    Scaffold(
        topBar = {
            // The top app bar displaying the title.
            TopAppBar(
                title = { Text("AI & ML Masterkit", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        // The bottom bar will contain our banner ad placeholder.
        bottomBar = {
            AdBannerPlaceholder()
        }
    ) { innerPadding ->
        // The main content of the app.
        HomeScreen(
            courses = sampleCourses,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

// --- Screens ---
@Composable
fun HomeScreen(courses: List<Course>, modifier: Modifier = Modifier) {
    // LazyColumn is an efficient way to display a scrollable list of items.
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(courses) { course ->
            CourseCard(course = course)
        }
    }
}

// --- Reusable UI Components ---
@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.DarkGray), // Neo-brutalist style border
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(0.dp) // No shadows for a flat look
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = course.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = course.description,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* TODO: Handle course selection */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                shape = androidx.compose.ui.graphics.RectangleShape // Sharp corners
            ) {
                Text(text = "Start Learning", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun AdBannerPlaceholder() {
    // This is a placeholder for a real AdMob banner.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFF1E1E1E))
            .border(1.dp, Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Ad Banner Placeholder",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}


// --- Preview ---
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun AppPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF00FF7F),
            background = Color(0xFF121212),
            onBackground = Color.White,
            surface = Color(0xFF1E1E1E),
            onSurface = Color.White
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            App()
        }
    }
}
