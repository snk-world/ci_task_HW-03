// Сценарный конвейер;
node('master') {
	stage('Prepeare Source') {// Получение кода;
		// Получаем код из нашего Git-репозитория;
		git branch: 'main', url: 'https://github.com/snk-world/ci_task_HW-03'
	}
	stage('Update container') {// Компиляция и выполнение модульного тестирования;
		// Запуск Gradle для выполнения компиляции и модульного тестирования;
		sh "docker-compose up -d"
	}
	stage('clear workspace') {
		sh 'echo end'
	}
}
