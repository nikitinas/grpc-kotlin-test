package nikitinas.grpc.hello.gradle.tasks

import org.gradle.api.tasks.AbstractExecTask
import org.gradle.api.tasks.TaskAction

class OpenBrowser extends AbstractExecTask {

	private boolean windows;

	public OpenBrowser() {
		super(OpenBrowser.class);
		String os = System.getProperty('os.name').toLowerCase();
		windows = os.contains('windows');
	}

	@Override
	@TaskAction
	protected void exec() {
		List<String> commandLine = this.getCommandLine();
		if(windows) {
			commandLine[0] = 'start ' + commandLine[0]
			commandLine.addAll(0, ['cmd', '/c'])
		}else {
			commandLine.add(0, 'open')
		}
		this.setCommandLine(commandLine);
		super.exec();
	}
}
