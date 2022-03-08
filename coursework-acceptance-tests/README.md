# COMP0010 Acceptance Tests #

To execute acceptance tests, install Python 3.5 or later.

Build an JSH image named "jsh-test":

    cd /path/to/jsh
    docker build -t jsh-test .

Run acceptance tests:

    python acceptance-tests.py -v

To re-run a specific test, e.g. test_cat, use the following command:

    python acceptance-tests.py -v TestJSH.test_cat

If there are failures, check the source code for details.