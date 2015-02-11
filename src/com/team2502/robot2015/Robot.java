
package com.team2502.robot2015;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.team2502.robot2015.commands.ExampleCommand;
import com.team2502.robot2015.subsystems.DriveTrain;
import com.team2502.robot2015.subsystems.ExampleSubsystem;
import com.team2502.robot2015.subsystems.Forklift;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

//	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final DriveTrain driveTrain = DriveTrain.getInstance();
	public static OI oi;
	public static final Forklift forklift = new Forklift();
	
	private Image frame;
	private int cameraSession;

//    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        cameraSession = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(cameraSession);
        NIVision.IMAQdxStartAcquisition(cameraSession);
        // instantiate the command used for the autonomous period
//        autonomousCommand = new ExampleCommand();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
        updateCamera();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
//        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateCamera();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
//        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateCamera();
        forklift.updateForkliftDashboard();
        driveTrain.updateDriveDashboard();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        updateCamera();
    }
    
    private void updateCamera() {
        NIVision.IMAQdxGrab(cameraSession, frame, 1);
        CameraServer.getInstance().setImage(frame);
    }
}
