package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class View {
	protected static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	protected int userId;

	protected abstract void start();
	protected abstract void proceed();
	protected abstract void end();
	public final void display(){
		start();
		proceed();
		end();
	}
}
