package nikitinas.grpc.hello.gradle.tasks

import org.gradle.api.tasks.AbstractExecTask
import org.gradle.api.tasks.TaskAction

class BashExec extends AbstractExecTask {

	private boolean windows;

	private String bashPath;

	public BashExec() {
		super(BashExec.class);
		String os = System.getProperty('os.name').toLowerCase();
		windows = os.contains('windows');
		bashPath = windows ? 'C:\\Program Files\\Git\\bin\\bash.exe' : 'sh';
	}

	@Override
	@TaskAction
	protected void exec() {
		List<String> commandLine = this.getCommandLine();
		commandLine.add(0, bashPath);
		this.setCommandLine(commandLine);
		super.exec();
	}
}
