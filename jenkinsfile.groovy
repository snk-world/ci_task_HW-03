def startNotify() {
  echo 'notify'
  // send  email
  emailext (
      subject: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    ) 	
}

node('master') {
	stage('Prepeare Source') {// Получение кода;
		git branch: 'main', url: 'https://github.com/snk-world/ci_task_HW-03'
	}
	stage('Update container') {
		sh "docker-compose down"
		sh "docker-compose up -d"
	}
	stage('check status') {
		def error = false
		def response = sh(script: " curl -I http://localhost:9889 | grep HTTP | awk '{print \$2}'", returnStdout: true).trim()
		echo response
		if (response != '200') {
			echo 'not ok'
			error = true
		} else {
			echo 'http status ok'
		} 
		
		def md5http= sh(script: "curl -sL http://localhost:9889/index.html | md5sum | cut -d ' ' -f 1", returnStdout:true).trim()	

		def md5local= sh(script: "md5sum www/index.html | cut -d ' ' -f 1", returnStdout:true).trim()	
		if (md5http != md5local){
			echo 'not ok'
			error = true
		} else {
			echo 'md5 sum ok: $md5http'
		}

		if (error==true) {
			echo 'error'
		}
		startNotify()

	}
	stage('clear workspace') {
		//sh 'docker-compose down'
		echo 'end'
		cleanWs()
	}
}
