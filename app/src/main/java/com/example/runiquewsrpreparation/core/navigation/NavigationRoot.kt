package com.example.runiquewsrpreparation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.runiquewsrpreparation.auth.presentation.intro.IntroScreenRoot
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = if(isLoggedIn) RunGraph else AuthGraph
    ) {
        authGraph(navController)
        runGraph(navController)
    }

}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<AuthGraph>(
        startDestination = AuthIntro
    ) {
        composable<AuthIntro> {
            IntroScreenRoot (
                onSignUpClick = { navController.navigate(AuthRegister) },
                onSignInClick = { navController.navigate(AuthLogin) }
            )
        }
        composable<AuthLogin> {  }
        composable<AuthRegister> {  }
    }
}

private fun NavGraphBuilder.runGraph(navController: NavHostController) {
    navigation<RunGraph>(
        startDestination = RunOverview
    ) {
        composable<RunOverview> {  }
        composable<RunActive> {  }
    }
}

@Serializable
object AuthGraph

@Serializable
object AuthIntro

@Serializable
object AuthRegister

@Serializable
object AuthLogin

@Serializable
object RunGraph

@Serializable
object RunOverview

@Serializable
object RunActive

