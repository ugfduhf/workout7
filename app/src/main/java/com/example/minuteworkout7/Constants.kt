package com.example.minuteworkout7

object Constants {
    fun defaultExercise():ArrayList<exerciseModel>{
        var exerciseList = ArrayList<exerciseModel>()
        var jumpingJacks = exerciseModel(

            1,
            "Jumping Jacks",
            R.drawable.yas,
            false,
            false
        )
        exerciseList.add(jumpingJacks)
        //exerciseList.add(jumpingJacks)

        var pushUpAndRotation = exerciseModel(2, "Push up Rotation", R.drawable.yas33, false, false)
        exerciseList.add(pushUpAndRotation)

        var lunges = exerciseModel(
            3,
            "Lunges",
            R.drawable.yas,
            false,
            false
        )
        exerciseList.add(lunges)
        return exerciseList
    }

}