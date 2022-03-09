// Сценарный конвейер;
node('master') {
	stage('Prepeare Source') {// Получение кода;
		git branch: 'main', url: 'https://github.com/snk-world/ci_task_HW-03'
	}
	stage('Update container') {
		sh "docker-compose down"
		sh "docker-compose up -d"
	}
	stage('check status') {
		def response = sh(script: " curl -I http://localhost:9889 | grep HTTP | awk '{print $2}'", returnStdout: true).trim()
		echo response
		if (response != '200') {
			echo not ok
		} else {
			echo ok
		} 	
	}
	stage('clear workspace') {
		//sh 'docker-compose down'
		sh 'echo end'
	}
}
