package com.tricakrawala.batikpedia

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.navigation.Screen
import com.tricakrawala.batikpedia.screen.splash.SplashScreenFirst
import com.tricakrawala.batikpedia.screen.splash.SplashScreenSecond
import com.tricakrawala.batikpedia.screen.splash.SplashScreenThird

@Composable
fun SplashApp(
    modifier: Modifier = Modifier,

){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashFirst.route
    ){
        composable(Screen.SplashFirst.route){
            SplashScreenFirst(navController = navController)
        }
        composable(Screen.SplashSecond.route){
            SplashScreenSecond(navController = navController)
        }
        composable(Screen.SplashThird.route){
            SplashScreenThird(navController = navController)
        }
    }
}