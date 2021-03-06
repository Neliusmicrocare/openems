package io.openems.edge.controller.asymmetric.fixreactivepower;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.openems.common.exceptions.OpenemsError.OpenemsNamedException;
import io.openems.edge.common.component.AbstractOpenemsComponent;
import io.openems.edge.common.component.ComponentManager;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.controller.api.Controller;
import io.openems.edge.ess.api.ManagedAsymmetricEss;
import io.openems.edge.ess.power.api.Phase;
import io.openems.edge.ess.power.api.PowerException;
import io.openems.edge.ess.power.api.Pwr;
import io.openems.edge.ess.power.api.Relationship;

@Designate(ocd = Config.class, factory = true)
@Component(name = "Controller.Asymmetric.FixReactivePower", immediate = true, configurationPolicy = ConfigurationPolicy.REQUIRE)
public class AsymmetricFixReactivePower extends AbstractOpenemsComponent implements Controller, OpenemsComponent {

	private final Logger log = LoggerFactory.getLogger(AsymmetricFixReactivePower.class);

	@Reference
	protected ComponentManager componentManager;

	private Config config;

	public AsymmetricFixReactivePower() {
		Utils.initializeChannels(this).forEach(channel -> this.addChannel(channel));
	}

	@Activate
	void activate(ComponentContext context, Config config) {
		super.activate(context, config.id(), config.enabled());
		this.config = config;
	}

	@Deactivate
	protected void deactivate() {
		super.deactivate();
	}

	@Override
	public void run() throws OpenemsNamedException {
		ManagedAsymmetricEss ess = this.componentManager.getComponent(this.config.ess_id());

		this.addConstraint(ess, Phase.L1, this.config.powerL1());
		this.addConstraint(ess, Phase.L2, this.config.powerL2());
		this.addConstraint(ess, Phase.L3, this.config.powerL3());
	}

	private void addConstraint(ManagedAsymmetricEss ess, Phase phase, int power) {
		// adjust value so that it fits into Min/MaxActivePower
		int calculatedPower = ess.getPower().fitValueIntoMinMaxPower(ess, phase, Pwr.REACTIVE, power);

		/*
		 * set result
		 */
		try {
			ess.addPowerConstraintAndValidate("AymmetricFixReactivePower " + phase, phase, Pwr.REACTIVE,
					Relationship.EQUALS, calculatedPower);
		} catch (PowerException e) {
			this.logError(this.log, e.getMessage());
		}
	}
}
