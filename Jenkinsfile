node { 	
	stage 'Checkout' 		
		checkout scm 	
		
	stage 'Build' 		
		echo "My branch is: ${env.BRANCH_NAME}" 		
		//build your gradle flavor, passes the current build number as a parameter to gradle 		
		sh "gradlew clean test assemble -PVERSION=${env.BUILD_NUMBER}" 
		
}

// Pulls the android flavor out of the branch name the branch is prepended with /QA_ 
@NonCPS def flavor(branchName) {
   def matcher = (branchName =~ /QA_([a-z_]+)/)
   assert matcher.matches()
   matcher[0][1] 
}