/*******************************************************************************
 * Copyright 2014 Pawel Pastuszak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package pl.kotcrab.vis.ui.widget;

import pl.kotcrab.vis.ui.InputValidator;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;

public class VisValidableTextField extends VisTextField {
	private Array<InputValidator> validators;

	public VisValidableTextField (InputValidator... validators) {
		this.validators = new Array<InputValidator>();

		for (InputValidator validator : validators)
			this.validators.add(validator);

		addListener(new InputListener() {
			@Override
			public boolean keyTyped (InputEvent event, char character) {
				validateInput();
				return false;
			}
		});
	}

	private void validateInput () {
		for (InputValidator validator : validators) {
			if (validator.validateInput(getText()) == false) {
				setInputValid(false);
				return;
			}
		}

		setInputValid(true);
	}

	public void addValidator (InputValidator validator) {
		validators.add(validator);
	}

}