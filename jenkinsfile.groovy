// Сценарный конвейер;
node('master') {
	stage('Source') {// Получение кода;
		// Получаем код из нашего Git-репозитория;
		git branch: 'main', url: 'https://github.com/snk-world/ci_task_11_5.git'
	}
	stage('Compile') {// Компиляция и выполнение модульного тестирования;
		// Запуск Gradle для выполнения компиляции и модульного тестирования;
		sh "echo 123"
		sh "set +x; mysql --user rfamro --host mysql-rfam-public.ebi.ac.uk --port 4497 --database Rfam <script.sql"
	}
	stage('exit') {
		sh 'echo end'
	}
}
